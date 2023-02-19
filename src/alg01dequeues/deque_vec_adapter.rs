use super::deque::Deque;

pub struct DequeVecAdapter<T> {
    vec: Vec<T>,
}

impl<T> DequeVecAdapter<T> {
    pub fn new() -> Self {
        DequeVecAdapter {
            vec: Vec::new()
        }
    }
}

impl<T> Deque<T> for DequeVecAdapter<T> {
    fn add_last(&mut self, item: T) {
        self.vec.push(item)
    }

    fn as_slice(&self) -> &[T] {
        self.vec.as_slice()
    }
}

#[cfg(test)]
mod tests {
    #[test]
    fn test() {
        assert_eq!(1, 1)
    }
}

#[cfg(test)]
mod test_vec_usage {
    #[test]
    fn test_grow_not_shrink() {
        let mut v: Vec<i32> = Vec::new();
        v.push(1);
        v.push(2);
        v.push(3);
        assert_eq!(3, v.len());
        assert_eq!(4, v.capacity());
        v.push(4);
        v.push(5);
        assert_eq!(5, v.len());
        assert_eq!(8, v.capacity());
        v.pop();
        v.pop();
        v.pop();
        v.pop();
        assert_eq!(1, v.len());
        assert_eq!(8, v.capacity());
    }

    #[test]
    fn test_insert() {
        let mut v: Vec<i32> = Vec::new();
        v.insert(0, 3);
        v.insert(0, 1);
        v.insert(1, 2);
        assert_eq!([1, 2, 3], v.as_slice())
    }
}