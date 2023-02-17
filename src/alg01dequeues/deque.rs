pub trait Deque<T> {
    fn add_last(&mut self, item: T);
    fn as_slice(&self) -> &[T];
}