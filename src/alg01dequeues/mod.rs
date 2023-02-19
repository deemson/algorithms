mod deque;
mod deque_vec_adapter;
mod array_deque;

pub use deque::Deque;
pub use array_deque::ArrayDeque;
pub use deque_vec_adapter::DequeVecAdapter;

/// A simple struct that implements `Drop` to be used
/// in deque tests to detect that items are dropped correctly
struct DropDetector<'a> {
    is_dropped: &'a mut bool,
}

impl<'a> Drop for DropDetector<'a> {
    fn drop(&mut self) {
        *self.is_dropped = true;
    }
}

/// Using macro to create common test suite for multiple `Deque` implementations
macro_rules! deque_tests {
    ($($name:ident: $deque_type:ident,)*) => {
        $(
            mod $name {
                use super::*;

                #[test]
                fn adds_last_simple_3_numbers() {
                    let mut deque = $deque_type::<i32>::new();
                    deque.add_last(1);
                    deque.add_last(2);
                    deque.add_last(3);
                    assert_eq!([1, 2, 3], deque.as_slice())
                }

                /// Empty deque should drop correctly
                #[test]
                fn empty_drops_ok() {
                    let deque = $deque_type::<()>::new();
                    drop(deque);
                }

                /// Passing `DropDetector` to check if a single item in `Deque` drops correctly
                #[test]
                fn single_item_drops_ok() {
                    let mut is_dropped = false;
                    let mut deque = $deque_type::<DropDetector>::new();
                    deque.add_last(DropDetector{is_dropped: &mut is_dropped});
                    drop(deque);
                    assert!(is_dropped);
                }

                /// Same as above only for multiple items
                #[test]
                fn multiple_items_drop_ok() {
                    let mut drop_flags = vec![false, false, false];
                    let mut deque = $deque_type::<DropDetector>::new();
                    for is_dropped in drop_flags.iter_mut() {
                        deque.add_last(DropDetector{is_dropped});
                    }
                    drop(deque);
                    assert_eq!([true, true, true], drop_flags.as_slice());
                }
            }
        )*
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn marker() {
        assert!(true);
    }

    deque_tests! {
        deque_vec_adapter: DequeVecAdapter,
        array_deque: ArrayDeque,
    }
}
