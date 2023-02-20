use std::alloc;
use std::ptr::NonNull;
use super::deque::Deque;

const MIN_NON_ZERO_CAPACITY: usize = 4;

/// `ArrayDeque<T>` uses chunks of contiguous manually allocated memory for `Deque<T>`
/// implementation. They are treated as a substitution for arrays and serve a similar purpose.
/// `ArrayDeque<T>` re-allocates memory as necessary, imitating array grow and shrink functionality.
pub struct ArrayDeque<T> {
    non_null_ptr: NonNull<T>,
    capacity: usize,
    len: usize,
    first_item_idx: usize,
    last_item_idx: usize,
}

impl<T> ArrayDeque<T> {
    pub fn new() -> Self {
        ArrayDeque {
            non_null_ptr: NonNull::dangling(),
            capacity: 0,
            len: 0,
            first_item_idx: 0,
            last_item_idx: 0,
        }
    }

    fn item_size(&self) -> usize {
        std::mem::size_of::<T>()
    }

    fn item_align(&self) -> usize {
        std::mem::align_of::<T>()
    }

    fn grow_from_zero(&mut self) {
        let layout = alloc::Layout::array::<T>(MIN_NON_ZERO_CAPACITY).expect("initial ArrayDeque memory layout exceeds isize::MAX");
        let ptr = unsafe { alloc::alloc(layout) } as *mut T;
        self.non_null_ptr = NonNull::new(ptr).expect("pointer to initial ArrayDeque allocated memory is null");
        self.capacity = MIN_NON_ZERO_CAPACITY;
    }

    fn grow_if_required(&mut self) {
        if self.capacity == 0 {
            self.grow_from_zero();
            return;
        }
        if self.len < self.capacity {
            return;
        }
        let new_capacity = self.capacity.checked_mul(2).expect("ArrayDeque capacity exceeds usize::MAX");
        let size = self.item_size() * self.capacity;
        let new_size = self.item_size() * new_capacity;
        let align = self.item_align();
        // Adding the modulo remainder to size will make it properly aligned for capacity of aligned items
        // this is one of the safety requirements for creating memory layout below
        size.checked_add(size % align).expect("ArrayDeque aligned size exceeds usize::MAX");
        // another safety requirement for creating memory layout below
        assert!(size < isize::MAX as usize, "ArrayDeque aligned size exceeds isize::MAX");
        self.non_null_ptr = unsafe {
            let layout = alloc::Layout::from_size_align_unchecked(size, align);
            let ptr = alloc::realloc(self.non_null_ptr.as_ptr() as *mut u8, layout, new_size);
            NonNull::new(ptr as *mut T).expect("pointer to ArrayDeque re-allocated memory is null")
        };
        self.capacity = new_capacity;
    }
}

impl<T> Deque<T> for ArrayDeque<T> {
    fn add_last(&mut self, item: T) {
        assert_ne!(self.item_size(), 0, "ArrayDeque cannot allocate memory for zero-sized items");
        self.grow_if_required();
        unsafe { self.non_null_ptr.as_ptr().add(self.last_item_idx).write(item) };
        self.last_item_idx += 1;
        if self.last_item_idx == self.capacity {
            self.last_item_idx = 0;
        }
        self.len += 1;
    }

    fn as_slice(&self) -> &[T] {
        unsafe { std::slice::from_raw_parts(self.non_null_ptr.as_ptr(), self.len) }
    }
}

impl<T> Drop for ArrayDeque<T> {
    fn drop(&mut self) {
        if self.capacity == 0 {
            return;
        }
        let pointer = self.non_null_ptr.as_ptr();
        unsafe {
            let raw_slice_pointer = std::slice::from_raw_parts_mut(pointer, self.len);
            let layout = alloc::Layout::from_size_align_unchecked(
                self.item_size() * self.capacity,
                self.item_align(),
            );
            std::ptr::drop_in_place(raw_slice_pointer);
            alloc::dealloc(pointer as *mut u8, layout);
        }
    }
}