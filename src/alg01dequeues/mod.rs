mod deque;
mod deque_vec_adapter;
mod array_deque;

pub use deque::Deque;
pub use array_deque::ArrayDeque;
pub use deque_vec_adapter::DequeueVecAdapter;

#[cfg(test)]
mod tests {
    use super::*;

    fn deque_constructors<T>() -> Vec<Box<fn() -> dyn Deque<T>>> {
        let mut v = Vec::<Box<fn() -> dyn Deque<T>>>::new();
        v.push(Box::new(|| DequeueVecAdapter::new()));
        v.push(Box::new(|| ArrayDeque::new()));
        v
    }

    fn all_deques<T: 'static>() -> Vec<Box<dyn Deque<T>>> {
        let mut v: Vec<Box<dyn Deque<T>>> = Vec::new();
        v.push(Box::new(DequeueVecAdapter::new()));
        v.push(Box::new(ArrayDeque::new()));
        v
    }

    #[test]
    fn test_add_last_simple_3_numbers() {
        for deque in all_deques::<i32>().iter_mut() {
            deque.add_last(1);
            deque.add_last(2);
            deque.add_last(3);
            assert_eq!([1, 2, 3], deque.as_slice())
        }
    }

    // struct Droppable<'a> {
    //     is_dropped: &'a bool,
    // }
    //
    // impl<'a> Drop for Droppable<'a> {
    //     fn drop(&mut self) {
    //         self.is_dropped = &true
    //     }
    // }
    //
    // #[test]
    // fn test_single_item_dropped_correctly() {
    //     for deque in all_deques::<Droppable>().iter_mut() {
    //         let drop_flag = false;
    //         let drop_flag_ref = &drop_flag;
    //         {
    //             let deque = deque;
    //             deque.add_last(Droppable{is_dropped: drop_flag_ref});
    //         }
    //     }
    // }
}