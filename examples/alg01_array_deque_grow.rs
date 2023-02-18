use algorithms::alg01dequeues::{ArrayDeque, Deque};

fn main() {
    let mut deque = ArrayDeque::<i32>::new();
    deque.add_last(1);
    deque.add_last(2);
    deque.add_last(3);
    deque.add_last(4);
    deque.add_last(5);

    assert_eq!([1, 2, 3, 4, 5], deque.as_slice())
}
