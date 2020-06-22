package alg05symboltables

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class TestSymbolTables {
    private companion object {
        @JvmStatic
        fun <K : Comparable<K>, V> symbolTables() = listOf(
            ST1UnorderedLinkedListSymbolTable<K, V>(),
            ST2RankedBinarySearchSymbolTable<K, V>(),
            ST3RedBlackBinarySearchTreeSymbolTable<K, V>()
        )
    }

    @ParameterizedTest()
    @MethodSource("symbolTables")
    fun `test it works`(st: SymbolTable<String, Int>) {
        assertTrue(st.isEmpty)
        assertFalse(st.contains("some"))
        st["one"] = 1
        assertTrue(st.contains("one"))
        assertFalse(st.contains("some"))
        st["two"] = 2
        assertFalse(st.contains("some"))
        assertEquals(2, st["two"])
        st["two"] = 4
        assertEquals(4, st["two"])
        st["three"] = 3
        assertEquals(3, st.size)
        assertEquals(setOf("one", "two", "three"), st.keys().asSequence().toSet())
        assertEquals(setOf(1, 4, 3), st.values().asSequence().toSet())
        assertEquals(setOf(Pair("one", 1), Pair("two", 4), Pair("three", 3)), st.toSet())
        st.delete("two")
        assertEquals(setOf(Pair("one", 1), Pair("three", 3)), st.toSet())
    }

    @ParameterizedTest()
    @MethodSource("symbolTables")
    fun `test get and delete non-existing keys`(st: SymbolTable<String, Int>) {
        // empty ST
        var e = assertThrows(IllegalKeyException::class.java) {
            st["some"]
        }
        assertEquals("some", e.key)
        assertEquals("key some is not present in the table", e.message!!)
        e = assertThrows(IllegalKeyException::class.java) {
            st.delete("some")
        }
        assertEquals("some", e.key)
        // adding the key last
        st["a"] = 42
        e = assertThrows(IllegalKeyException::class.java) {
            st["some"]
        }
        assertEquals("some", e.key)
        e = assertThrows(IllegalKeyException::class.java) {
            st.delete("some")
        }
        assertEquals("some", e.key)
        // adding the key in-between keys
        st["z"] = 100500
        e = assertThrows(IllegalKeyException::class.java) {
            st["some"]
        }
        assertEquals("some", e.key)
        e = assertThrows(IllegalKeyException::class.java) {
            st.delete("some")
        }
        assertEquals("some", e.key)
    }

    @ParameterizedTest()
    @MethodSource("symbolTables")
    fun `test additional cases for red black tree`(st: SymbolTable<String, Int>) {
        st["c"] = 3
        st["b"] = 2
        st["d"] = 4
        assertEquals(4, st["d"])
        assertThrows(IllegalKeyException::class.java) {
            st["a"]
        }
    }
}
