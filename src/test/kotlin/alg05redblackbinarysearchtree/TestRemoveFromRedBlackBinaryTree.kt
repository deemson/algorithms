package alg05redblackbinarysearchtree

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class TestRemoveFromRedBlackBinaryTree {
    private var t: RedBlackBinarySearchTree<String> = RedBlackBinarySearchTreeImpl()

    @BeforeEach
    fun `initialize tree`() {
        t = RedBlackBinarySearchTreeImpl()
    }

    @AfterEach
    fun `check tree`() {
        t.doAssertions()
    }

    @Test
    fun `empty tree and incorrect value`() {
        val e = assertThrows(RedBlackIsEmptyException::class.java) {
            t.remove("some")
        }
        assertEquals("tree is empty", e.message!!)
    }

    @Test
    fun `non empty tree and incorrect value`() {
        t.add("a")
        val e = assertThrows(RedBlackIllegalValueException::class.java) {
            t.remove("some")
        }
        assertEquals("some", e.value)
        assertEquals("value some is not present in the tree", e.message!!)
    }

    @Test
    fun `single value`() {
        t.add("a")
        t.remove("a")
        assertTrue(t.isEmpty)
    }

    @Test
    fun `left of two values`() {
        t.add("a")
        t.add("b")
        t.remove("a")
        assertEquals(1, t.root.size)
    }

    @Test
    fun `left of three values`() {
        t.add("a")
        t.add("b")
        t.add("c")
        t.remove("a")
        val c = t.root
        assertEquals("c", c.value)
        assertEquals(2, c.size)
        assertTrue(c.isBlack)
        val b = c.left
        assertEquals("b", b.value)
        assertEquals(1, b.size)
        assertTrue(b.isRed)
    }

    @Test
    fun `left of right is red when removing on the left (move left scenario)`() {
        t.add("b")
        t.add("a")
        t.add("d")
        t.add("c")
        t.remove("a")
    }

    @Test
    fun `left of right is red not at root`() {
        t.add("e")
        t.add("b")
        assertTrue(t.root.left.isRed)
        t.add("f")
        assertEquals("e", t.root.value)
        assertTrue(t.root.left.isBlack)
        assertTrue(t.root.right.isBlack)
        t.add("g")
        assertEquals("e", t.root.value)
        assertTrue(t.root.right.left.isRed)
        t.add("a")
        assertEquals("e", t.root.value)
        assertEquals("b", t.root.left.value)
        assertTrue(t.root.left.isBlack)
        assertEquals("a", t.root.left.left.value)
        assertTrue(t.root.left.left.isRed)
        t.add("d")
        assertEquals("e", t.root.value)
        assertEquals("b", t.root.left.value)
        assertTrue(t.root.left.isRed)
        assertEquals("a", t.root.left.left.value)
        assertTrue(t.root.left.left.isBlack)
        assertEquals("d", t.root.left.right.value)
        assertTrue(t.root.left.left.isBlack)
        t.add("c")
        assertEquals("e", t.root.value)
        assertEquals("b", t.root.left.value)
        assertTrue(t.root.left.isRed)
        assertEquals("a", t.root.left.left.value)
        assertTrue(t.root.left.left.isBlack)
        assertEquals("d", t.root.left.right.value)
        assertTrue(t.root.left.left.isBlack)
        assertEquals("c", t.root.left.right.left.value)
        assertTrue(t.root.left.right.left.isRed)
        t.remove("a")
    }

    @Test
    fun `remove non leaf`() {
        t.add("b")
        t.add("a")
        t.add("d")
        t.add("c")
        t.remove("b")
    }

    @Test
    fun `remove red leaf not at root`() {
        t.add("e")
        t.add("b")
        t.add("f")
        t.add("g")
        t.add("a")
        t.add("d")
        t.add("c")
        t.remove("c")
    }

    @Test
    fun `move left leaf node`() {
        t.add("d")
        t.add("c")
        t.add("e")
        t.add("a")
        t.add("f")
        t.add("b")
        t.add("g")
        t.remove("b")
    }

    @Test
    fun `left node is red when deleting on the right`() {
        t.add("d")
        t.add("b")
        t.add("e")
        t.add("a")
        t.add("c")
        t.remove("e")
    }

    @Test
    fun `left of left is red when removing on the right (move right scenario)`() {
        t.add("c")
        t.add("b")
        t.add("d")
        t.add("a")
        t.remove("d")
    }
}
