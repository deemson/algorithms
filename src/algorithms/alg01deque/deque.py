from abc import ABCMeta, abstractmethod
from typing import TypeVar, Iterable

T = TypeVar("T")


class Deque(Iterable[T], metaclass=ABCMeta):
    def __init__(self) -> None:
        self._size: int = 0

    @property
    def size(self) -> int:
        return self._size

    @property
    def is_empty(self) -> bool:
        return self.size == 0

    @abstractmethod
    def push_head(self, item: T) -> None:  # pragma: no cover
        raise NotImplementedError()

    @abstractmethod
    def push_tail(self, item: T) -> None:  # pragma: no cover
        raise NotImplementedError()

    @abstractmethod
    def push_at(self, index: int, item: T) -> None:  # pragma: no cover
        raise NotImplementedError()

    def _assert_not_empty(self) -> None:
        if self.is_empty:
            raise AssertionError("deque is empty: cannot pop elements from it")

    @abstractmethod
    def pop_head(self) -> T:  # pragma: no cover
        raise NotImplementedError()

    @abstractmethod
    def pop_tail(self) -> T:  # pragma: no cover
        raise NotImplementedError()

    @abstractmethod
    def pop_at(self, index: int) -> T:  # pragma: no cover
        raise NotImplementedError()

    @abstractmethod
    def __getitem__(self, index: int) -> T:  # pragma: no cover
        raise NotImplementedError()

    @abstractmethod
    def __setitem__(self, index: int, item: T) -> None:  # pragma: no cover
        raise NotImplementedError()

    def swap(self, index1: int, index2: int) -> None:
        tmp = self[index1]
        self[index1] = self[index2]
        self[index2] = tmp
