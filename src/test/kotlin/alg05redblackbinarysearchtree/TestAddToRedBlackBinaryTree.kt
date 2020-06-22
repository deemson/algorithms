package alg05redblackbinarysearchtree

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class TestAddToRedBlackBinaryTree {
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
    fun `single value`() {
        t.add("a")
        val a = t.root
        assertTrue(a.isBlack)
        assertEquals(1, a.size)
        assertEquals("a", a.value)
    }

    @Test
    fun `rotate counterclockwise`() {
        t.add("a")
        // at this point of we're inserting a value that must go to the right
        // of the root, creating red link
        // the tree fixes itself to lean to the left by rotating counterclockwise
        t.add("b")
        val b = t.root
        assertEquals("b", b.value)
        assertEquals(2, b.size)
        assertTrue(b.isBlack)
        assertFalse(b.hasRight)
        assertTrue(b.hasLeft)
        val a = b.left
        assertEquals("a", a.value)
        assertEquals(1, a.size)
        assertTrue(a.isRed)
    }

    @Test
    fun `push red up at root`() {
        t.add("b")
        // adding red node to the left
        t.add("a")
        // adding red node to the right
        t.add("c")
        // the way the tree fixes this situation is by pushing red up
        // that means splitting the 4 node "abc" into node "a" (black) and "c" (black)
        // pushing "b" into its parent by marking it red
        val b = t.root
        assertEquals("b", b.value)
        assertEquals(3, b.size)
        // as "b" is root it is switched to red temporarily and then back to black
        assertTrue(b.isBlack)
        assertTrue(b.hasLeft)
        val a = b.left
        assertEquals("a", a.value)
        assertEquals(1, a.size)
        assertTrue(a.isBlack)
        assertTrue(b.hasRight)
        val c = b.right
        assertEquals("c", c.value)
        assertEquals(1, c.size)
        assertTrue(c.isBlack)
    }

    @Test
    fun `push red up`() {
        // expanding on the test above: let's show that the parent node actually changes color to red
        t.add("d")
        t.add("b")
        t.add("e")
        t.add("a")
        t.add("c")
        val d = t.root
        assertEquals("d", d.value)
        assertEquals(5, d.size)
        assertTrue(d.isBlack)
        val e = d.right
        assertEquals("e", e.value)
        assertEquals(1, e.size)
        assertTrue(e.isBlack)
        val b = d.left
        assertEquals("b", b.value)
        assertEquals(3, b.size)
        assertTrue(b.isRed)
        val a = b.left
        assertEquals("a", a.value)
        assertEquals(1, a.size)
        assertTrue(a.isBlack)
        val c = b.right
        assertEquals("c", c.value)
        assertEquals(1, c.size)
        assertTrue(c.isBlack)
    }

    @Test
    fun `rotate counterclockwise and push red up at root`() {
        // slight addition to two tests above
        t.add("a")
        // rotation happens at this point
        t.add("b")
        t.add("c")
        val b = t.root
        assertEquals("b", b.value)
        assertEquals(3, b.size)
        assertTrue(b.isBlack)
        assertTrue(b.hasLeft)
        val a = b.left
        assertEquals("a", a.value)
        assertEquals(1, a.size)
        assertTrue(a.isBlack)
        assertTrue(b.hasRight)
        val c = b.right
        assertEquals("c", c.value)
        assertEquals(1, c.size)
        assertTrue(c.isBlack)
    }

    @Test
    fun `rotate counterclockwise and push red up`() {
        t.add("d")
        t.add("a")
        t.add("e")
        t.add("b")
        t.add("c")
        val d = t.root
        assertEquals("d", d.value)
        assertEquals(5, d.size)
        assertTrue(d.isBlack)
        val e = d.right
        assertEquals("e", e.value)
        assertEquals(1, e.size)
        assertTrue(e.isBlack)
        val b = d.left
        assertEquals("b", b.value)
        assertEquals(3, b.size)
        assertTrue(b.isRed)
        val a = b.left
        assertEquals("a", a.value)
        assertEquals(1, a.size)
        assertTrue(a.isBlack)
        val c = b.right
        assertEquals("c", c.value)
        assertEquals(1, c.size)
        assertTrue(c.isBlack)
    }

    @Test
    fun `rotate clockwise and push red up at root`() {
        t.add("c")
        t.add("b")
        t.add("a")
        val b = t.root
        assertEquals("b", b.value)
        assertEquals(3, b.size)
        assertTrue(b.isBlack)
        assertTrue(b.hasLeft)
        val a = b.left
        assertEquals("a", a.value)
        assertEquals(1, a.size)
        assertTrue(a.isBlack)
        assertTrue(b.hasRight)
        val c = b.right
        assertEquals("c", c.value)
        assertEquals(1, c.size)
        assertTrue(c.isBlack)
    }

    @Test
    fun `rotate clockwise and push red up`() {
        t.add("d")
        t.add("c")
        t.add("e")
        t.add("b")
        t.add("a")
        val d = t.root
        assertEquals("d", d.value)
        assertEquals(5, d.size)
        assertTrue(d.isBlack)
        val e = d.right
        assertEquals("e", e.value)
        assertEquals(1, e.size)
        assertTrue(e.isBlack)
        val b = d.left
        assertEquals("b", b.value)
        assertEquals(3, b.size)
        assertTrue(b.isRed)
        val a = b.left
        assertEquals("a", a.value)
        assertEquals(1, a.size)
        assertTrue(a.isBlack)
        val c = b.right
        assertEquals("c", c.value)
        assertEquals(1, c.size)
        assertTrue(c.isBlack)
    }

    @Test
    fun `rotate counterclockwise, clockwise and push red up at root`() {
        t.add("c")
        t.add("a")
        t.add("b")
        val b = t.root
        assertEquals("b", b.value)
        assertEquals(3, b.size)
        assertTrue(b.isBlack)
        assertTrue(b.hasLeft)
        val a = b.left
        assertEquals("a", a.value)
        assertEquals(1, a.size)
        assertTrue(a.isBlack)
        assertTrue(b.hasRight)
        val c = b.right
        assertEquals("c", c.value)
        assertEquals(1, c.size)
        assertTrue(c.isBlack)
    }

    @Test
    fun `rotate counterclockwise, clockwise and push red up`() {
        t.add("d")
        t.add("c")
        t.add("e")
        t.add("a")
        t.add("b")
        val d = t.root
        assertEquals("d", d.value)
        assertEquals(5, d.size)
        assertTrue(d.isBlack)
        val e = d.right
        assertEquals("e", e.value)
        assertEquals(1, e.size)
        assertTrue(e.isBlack)
        val b = d.left
        assertEquals("b", b.value)
        assertEquals(3, b.size)
        assertTrue(b.isRed)
        val a = b.left
        assertEquals("a", a.value)
        assertEquals(1, a.size)
        assertTrue(a.isBlack)
        val c = b.right
        assertEquals("c", c.value)
        assertEquals(1, c.size)
        assertTrue(c.isBlack)
    }
}
