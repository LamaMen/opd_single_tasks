package matrix

import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class MatrixTest {
    @Test
    fun `isEquals is true for two equals matrices`() {
        val first: Matrix = arrayOf(intArrayOf(1, 1, 1), intArrayOf(0, 0, 0))
        val second: Matrix = arrayOf(intArrayOf(1, 1, 1), intArrayOf(0, 0, 0))

        assertTrue { first.isEquals(second) }
    }

    @Test
    fun `isEquals is false for two not equals matrices`() {
        val first: Matrix = arrayOf(intArrayOf(0, 0, 0), intArrayOf(0, 0, 0))
        val second: Matrix = arrayOf(intArrayOf(1, 1, 1), intArrayOf(0, 0, 0))

        assertFalse { first.isEquals(second) }
    }

    @Test
    fun `isEquals is false for two matrices with different count lines`() {
        val first: Matrix = arrayOf(intArrayOf(1, 1, 1))
        val second: Matrix = arrayOf(intArrayOf(1, 1, 1), intArrayOf(0, 0, 0))

        assertFalse { first.isEquals(second) }
    }

    @Test
    fun `isEquals is false for two matrices with different count rows`() {
        val first: Matrix = arrayOf(intArrayOf(1, 1), intArrayOf(0, 0))
        val second: Matrix = arrayOf(intArrayOf(1, 1, 1), intArrayOf(0, 0, 0))

        assertFalse { first.isEquals(second) }
    }

    @Test
    fun `can swap lines for matrix`() {
        val expected: Matrix = arrayOf(intArrayOf(1, 1, 1), intArrayOf(0, 0, 0))
        val actual: Matrix = arrayOf(intArrayOf(0, 0, 0), intArrayOf(1, 1, 1))

        actual.swapLines(0, 1)

        assertTrue { expected.isEquals(actual) }
    }
}