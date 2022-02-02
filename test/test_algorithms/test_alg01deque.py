import pytest

from algorithms.alg01deque import ArrayDeque


class TestArrayDeque:
    def test_it_grows(self):
        d: ArrayDeque[int] = ArrayDeque([1, 2], capacity=2)
        # this line will trigger inner array grow
        d.push_tail(3)
        assert list(d) == [1, 2, 3]

    def test_it_shrinks(self):
        d: ArrayDeque[int] = ArrayDeque([1, 2, 3, 4], capacity=4)
        assert d.pop_tail() == 4
        assert d.pop_tail() == 3
        # this line will trigger inner array shrink
        assert d.pop_tail() == 2
        assert list(d) == [1]

    def test_push(self):
        d: ArrayDeque[str] = ArrayDeque()
        d.push_at(0, "one")
        d.push_at(1, "three")
        d.push_at(1, "two")
        assert list(d) == ["one", "two", "three"]

    def test_pop(self):
        d: ArrayDeque[str] = ArrayDeque(["one", "two", "three"])
        assert d.pop_at(1) == "two"
        assert d.pop_at(1) == "three"
        assert d.pop_at(0) == "one"
        assert d.is_empty

    def test_get_incorrect_index(self):
        d: ArrayDeque[bool] = ArrayDeque()
        with pytest.raises(ValueError) as exc_info:
            assert d[0]
        assert str(exc_info.value) == "cannot get index 0: deque is empty"
        d.push_tail(True)
        with pytest.raises(ValueError) as exc_info:
            assert d[1]
        assert str(exc_info.value) == "want index in [0, 0]; have index=1"

    def test_pop_empty(self):
        d: ArrayDeque[bool] = ArrayDeque()
        with pytest.raises(AssertionError) as exc_info:
            assert d.pop_tail()
        assert str(exc_info.value) == "dequeue is empty: cannot remove elements from it"

    def test_pop_head_index_reset(self):
        d: ArrayDeque[str] = ArrayDeque(capacity=2)
        # pushing this to head causes first_item_index to be set to capacity - 1
        d.push_head("sup")
        # so this pop causes the code to reset it to zero to be triggered
        assert d.pop_head() == "sup"

    def test_push_at_tail_index_reset(self):
        d: ArrayDeque[str] = ArrayDeque(["one", "two", "three"], capacity=4)
        # this push causes the code to reset tail_item_index to zero to be triggered
        d.push_at(1, "two-and-a-half")
