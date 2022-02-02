from typing import TypeVar

from algorithms.alg01deque import Deque

T = TypeVar("T")


class LinkedDeque(Deque[T]):
    def push_head(self, item: T) -> None:
        pass

    def push_tail(self, item: T) -> None:
        pass

    def push_at(self, index: int, item: T) -> None:
        pass

    def pop_head(self) -> T:
        pass

    def pop_tail(self) -> T:
        pass

    def pop_at(self, index: int) -> T:
        pass

    def __getitem__(self, index: int) -> T:
        pass

    def __setitem__(self, index: int, item: T) -> None:
        pass
