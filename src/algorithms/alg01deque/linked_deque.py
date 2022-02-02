from typing import TypeVar, Generic, Optional, Iterable, cast, Iterator

from algorithms.alg01deque import Deque

T = TypeVar("T")


class Node(Generic[T]):
    def __init__(self, item: T):
        self.item: T = item
        self.prev: Optional[Node[T]] = None
        self.next: Optional[Node[T]] = None


class LinkedDeque(Deque[T]):
    def __init__(self, iterable: Optional[Iterable[T]] = None) -> None:
        super().__init__()
        self._head: Optional[Node[T]] = None
        self._tail: Optional[Node[T]] = None
        if iterable:
            for item in iterable:
                self.push_tail(item)

    def _node_at(self, index: int):
        if self.is_empty:
            raise ValueError(f"cannot get index {index}: deque is empty")
        if index < 0 or index >= self.size:
            raise ValueError(f"want index in [0, {self.size - 1}]; have index={index}")
        node: Node[T] = cast(Node[T], self._head)
        current_index = 0
        while current_index < index:
            node = cast(Node[T], cast(Node[T], self._head).next)
            current_index += 1
        return node

    def __getitem__(self, index: int) -> T:
        return self._node_at(index).item

    def __setitem__(self, index: int, item: T) -> None:
        self._node_at(index).item = item

    def push_head(self, item: T) -> None:
        head = self._head
        self._head = Node(item)
        if self.is_empty:
            self._tail = self._head
        else:
            head = cast(Node[T], head)
            head.prev = self._head
            self._head.next = head
        self._size += 1

    def push_tail(self, item: T) -> None:
        tail = self._tail
        self._tail = Node(item)
        if self.is_empty:
            self._head = self._tail
        else:
            tail = cast(Node[T], tail)
            tail.next = self._tail
            self._tail.prev = tail
        self._size += 1

    def push_at(self, index: int, item: T) -> None:
        if index == 0:
            self.push_head(item)
        elif index == self.size:
            self.push_tail(item)
        else:
            node = self._node_at(index)
            new_node = Node(item)
            new_node.next = node
            new_node.prev = node.prev
            node.prev.next = new_node
            node.prev = new_node
            self._size += 1

    def pop_head(self) -> T:
        self._assert_not_empty()
        head = cast(Node[T], self._head)
        self._head = head.next
        self._size -= 1
        if self.is_empty:
            self._tail = None
        else:
            cast(Node[T], self._head).prev = None
        return head.item

    def pop_tail(self) -> T:
        self._assert_not_empty()
        tail = cast(Node[T], self._tail)
        self._tail = tail.prev
        self._size -= 1
        if self.is_empty:
            self._head = None
        else:
            cast(Node[T], self._tail).next = None
        return tail.item

    def pop_at(self, index: int) -> T:
        self._assert_not_empty()
        if index == 0:
            return self.pop_head()
        elif index == self.size - 1:
            return self.pop_tail()
        else:
            node = self._node_at(index)
            item = node.item
            node.prev.next = node.next
            node.next.prev = node.prev
            self._size -= 1
            return item

    def __iter__(self) -> Iterator[T]:
        node = self._head
        while node is not None:
            yield node.item
            node = node.next
