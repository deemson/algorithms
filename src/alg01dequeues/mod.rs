mod deque;
mod deque_vec_adapter;
mod array_deque;

pub use deque::Deque;
pub use array_deque::ArrayDeque;
pub use deque_vec_adapter::DequeueVecAdapter;

#[cfg(test)]
mod tests {
    use super::*;

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
}