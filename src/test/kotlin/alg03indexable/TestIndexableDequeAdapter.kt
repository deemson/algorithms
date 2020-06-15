package alg03indexable

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class TestIndexableDequeAdapter {
    @Test
    fun `test isEmpty due to multiple implementations`() {
        val indexable = indexableArrayDequeOf<Int>()
        assertTrue(indexable.isEmpty)
    }
}
