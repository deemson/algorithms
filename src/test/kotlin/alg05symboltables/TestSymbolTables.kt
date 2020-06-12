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
        fun <K, V> symbolTables() = listOf(ST1UnorderedLinkedListSymbolTable<K, V>())
    }

    @ParameterizedTest()
    @MethodSource("symbolTables")
    fun `test it works`(st: SymbolTable<String, Int>) {
        assertTrue(st.isEmpty)
        assertFalse(st.contains("some"))
        st["one"] = 1
        st["two"] = 2
        st["three"] = 3
        assertEquals(3, st.size)
        assertEquals(setOf("one", "two", "three"), st.keys().asSequence().toSet())
        assertEquals(setOf(1, 2, 3), st.values().asSequence().toSet())
        assertEquals(setOf(Pair("one", 1), Pair("two", 2), Pair("three", 3)), st.toSet())
        st.delete("two")
        assertEquals(setOf(Pair("one", 1), Pair("three", 3)), st.toSet())
    }

    @ParameterizedTest()
    @MethodSource("symbolTables")
    fun `test get and delete non-existing keys`(st: SymbolTable<String, Int>) {
        assertThrows(IllegalStateException::class.java) {
            st["some"]
        }
        assertThrows(IllegalStateException::class.java) {
            st.delete("some")
        }
    }
}
