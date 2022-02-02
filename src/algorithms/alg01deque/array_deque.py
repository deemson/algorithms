from typing import TypeVar, Union, List, cast, Iterable

from algorithms.alg01deque.deque import Deque

T = TypeVar("T")


class Empty:
    pass


EMPTY = Empty()


class ArrayDeque(Deque[T]):
    def __init__(self, iterable: Iterable[T] = None, capacity: int = 32):
        super().__init__()
        self._array: List[Union[Empty, T]] = [EMPTY] * capacity
        self._head_item_index: int = 0
        self._tail_item_index: int = 0
        if iterable:
            for item in iterable:
                self.push_tail(item)

    @property
    def capacity(self) -> int:
        return len(self._array)

    def _assert_index(self, index: int):
        if self.is_empty:
            raise ValueError(f"cannot get index {index}: deque is empty")
        if index < 0 or index >= self.size:
            raise ValueError(f"want index in [0, {self.size - 1}]; have index={index}")

    def _normalize_index(self, index: int) -> int:
        self._assert_index(index)
        return self._head_item_index + index - self.capacity

    def __getitem__(self, index: int) -> T:
        return cast(T, self._array[self._normalize_index(index)])

    def __setitem__(self, index: int, item: T) -> None:
        self._array[self._normalize_index(index)] = item

    def _resize(self, capacity: int):
        array: List[Union[Empty, T]] = [EMPTY] * capacity
        for index in range(self.size):
            array[index] = self[index]
        self._array = array
        self._head_item_index = 0
        self._tail_item_index = self.size

    def _grow_if_required(self) -> None:
        if self.size == self.capacity:
            self._resize(self.capacity * 2)

    def _shrink_if_required(self) -> None:
        if self.size > 0 and self.size == self.capacity // 4:
            self._resize(self.capacity // 2)

    def push_head(self, item: T) -> None:
        self._grow_if_required()
        self._head_item_index -= 1
        if self._head_item_index < 0:
            self._head_item_index = self.capacity - 1
        self._array[self._head_item_index] = item
        self._size += 1

    def push_tail(self, item: T) -> None:
        self._grow_if_required()
        self._array[self._tail_item_index] = item
        self._tail_item_index += 1
        if self._tail_item_index == self.capacity:
            self._tail_item_index = 0
        self._size += 1

    def push_at(self, index: int, item: T) -> None:
        self._grow_if_required()
        if index == 0:
            self.push_head(item)
        elif index == self.size:
            self.push_tail(item)
        else:
            self._assert_index(index)
            self._tail_item_index += 1
            if self._tail_item_index == self.capacity:
                self._tail_item_index = 0
            self._size += 1
            for remap_index in range(self.size - 1, index, -1):
                self[remap_index] = self[remap_index - 1]
            self[index] = item

    def _assert_not_empty(self) -> None:
        if self.is_empty:
            raise AssertionError("dequeue is empty: cannot remove elements from it")

    def pop_head(self) -> T:
        self._assert_not_empty()
        item: T = self[0]
        self._head_item_index += 1
        if self._head_item_index == self.capacity:
            self._head_item_index = 0
        self._size -= 1
        self._shrink_if_required()
        return item

    def pop_tail(self) -> T:
        self._assert_not_empty()
        item: T = self[self.size - 1]
        self._tail_item_index -= 1
        if self._tail_item_index < 0:
            self._tail_item_index = self.capacity - 1
        self._size -= 1
        self._shrink_if_required()
        return item

    def pop_at(self, index: int) -> T:
        if index == 0:
            return self.pop_head()
        elif index == self.size - 1:
            return self.pop_tail()
        else:
            for swap_index in range(index, self.size - 1):
                self.swap(swap_index, swap_index + 1)
            return self.pop_tail()
