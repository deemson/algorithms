use std::alloc;
use std::ptr::NonNull;
use super::deque::Deque;

/// `ArrayDeque<T>` uses chunks of contiguous manually allocated memory for `Deque<T>`
/// implementation. They are treated as a substitution for arrays and serve a similar purpose.
/// `ArrayDeque<T>` re-allocates memory as necessary, imitating array grow and shrink functionality.
pub struct ArrayDeque<T> {
    pointer: NonNull<T>,
    capacity: usize,
    length: usize,
}

impl<T> ArrayDeque<T> {
    pub fn new() -> Self {
        ArrayDeque {
            pointer: NonNull::dangling(),
            capacity: 0,
            length: 0,
        }
    }

    fn item_size(&self) -> usize {
        std::mem::size_of::<T>()
    }

    fn item_align(&self) -> usize {
        std::mem::align_of::<T>()
    }
}

impl<T> Deque<T> for ArrayDeque<T> {
    fn add_last(&mut self, item: T) {
        assert_ne!(self.item_size(), 0, "No zero sized types");
        if self.capacity == 0 {
            let layout = alloc::Layout::array::<T>(4).expect("Could not obtain alloc layout");
            let pointer = unsafe { alloc::alloc(layout) } as *mut T;
            let pointer = NonNull::new(pointer).expect("Could not allocate pointer");
            unsafe { pointer.as_ptr().write(item) }
            self.pointer = pointer;
            self.capacity = 4;
            self.length = 1;
        } else if self.length < self.capacity {
            let offset = self.length.checked_mul(self.item_size()).expect("Cannot reach memory location");
            assert!(offset < isize::MAX as usize, "Wrapped around isize");
            unsafe { self.pointer.as_ptr().add(self.length).write(item) }
            self.length += 1;
        } else {
            let new_capacity = self.capacity.checked_mul(2).expect("capacity wrapped around usize");
            let align = self.item_align();
            let size = self.item_size() * self.capacity;
            size.checked_add(size % align).expect("can't allocate");
            let pointer = unsafe {
                let layout = alloc::Layout::from_size_align_unchecked(size, align);
                let new_size = self.item_size() * new_capacity;
                let pointer = alloc::realloc(self.pointer.as_ptr() as *mut u8, layout, new_size);
                let pointer = NonNull::new(pointer as *mut T).expect("can't reallocate");
                pointer.as_ptr().add(self.length).write(item);
                pointer
            };
            self.pointer = pointer;
            self.capacity = new_capacity;
            self.length += 1;
        }
    }

    fn as_slice(&self) -> &[T] {
        unsafe { std::slice::from_raw_parts(self.pointer.as_ptr(), self.length) }
    }
}

impl<T> Drop for ArrayDeque<T> {
    fn drop(&mut self) {
        if self.capacity == 0 {
            return
        }
        let pointer = self.pointer.as_ptr();
        unsafe {
            let raw_slice_pointer = std::slice::from_raw_parts_mut(pointer, self.length);
            let layout = alloc::Layout::from_size_align_unchecked(
                self.item_size() * self.capacity,
                self.item_align(),
            );
            std::ptr::drop_in_place(raw_slice_pointer);
            alloc::dealloc(pointer as *mut u8, layout);
        }
    }
}