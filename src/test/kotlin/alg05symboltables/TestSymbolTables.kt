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
            ST2RankedBinarySearchSymbolTable<K, V>()
        )
    }

    @ParameterizedTest()
    @MethodSource("symbolTables")
    fun `test it works`(st: SymbolTable<String, Int>) {
        assertTrue(st.isEmpty)
        assertFalse(st.contains("some"))
        st["one"] = 1
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
        var e = assertThrows(IllegalArgumentException::class.java) {
            st["some"]
        }
        assertEquals("key \"some\" was not found", e.message!!)
        e = assertThrows(IllegalArgumentException::class.java) {
            st.delete("some")
        }
        assertEquals("key \"some\" was not found", e.message!!)
        // adding the key last
        st["a"] = 42
        e = assertThrows(IllegalArgumentException::class.java) {
            st["some"]
        }
        assertEquals("key \"some\" was not found", e.message!!)
        e = assertThrows(IllegalArgumentException::class.java) {
            st.delete("some")
        }
        assertEquals("key \"some\" was not found", e.message!!)
        // adding the key in-between keys
        st["z"] = 100500
        e = assertThrows(IllegalArgumentException::class.java) {
            st["some"]
        }
        assertEquals("key \"some\" was not found", e.message!!)
        e = assertThrows(IllegalArgumentException::class.java) {
            st.delete("some")
        }
        assertEquals("key \"some\" was not found", e.message!!)
    }
}
