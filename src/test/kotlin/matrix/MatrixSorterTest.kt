package matrix

import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class MatrixSorterTest {
    @Test
    fun `can sort by custom strategy`() {
        val sourceMatrix: Matrix = arrayOf(intArrayOf(1, 1, 1), intArrayOf(0, 0, 0), intArrayOf(1, 2, 2))
        val expectedMatrix: Matrix = arrayOf(intArrayOf(0, 0, 0), intArrayOf(1, 1, 1), intArrayOf(1, 2, 2))
        val lineProjectionStrategy = mockk<LineProjectionStrategy>()

        val lineSlot = slot<IntArray>()
        every { lineProjectionStrategy.projectLine(capture(lineSlot)) } answers {
            lineSlot.captured.sum()
        }

        val sorter = MatrixSorter(lineProjectionStrategy)
        val actualMatrix = sorter(sourceMatrix)
        assertTrue("matrices not equals") { actualMatrix.isEquals(expectedMatrix) }
    }

    @Test
    fun `can sort reversed by custom strategy`() {
        val sourceMatrix: Matrix = arrayOf(intArrayOf(0, 0, 0), intArrayOf(1, 1, 1), intArrayOf(1, 2, 2))
        val expectedMatrix: Matrix = arrayOf(intArrayOf(1, 2, 2), intArrayOf(1, 1, 1), intArrayOf(0, 0, 0))

        val lineProjectionStrategy = mockk<LineProjectionStrategy>()
        val lineSlot = slot<IntArray>()
        every { lineProjectionStrategy.projectLine(capture(lineSlot)) } answers {
            lineSlot.captured.sum()
        }

        val sorter = MatrixSorter(lineProjectionStrategy)
        val actualMatrix = sorter(sourceMatrix, reversed = true)
        assertTrue("matrices not equals") { actualMatrix.isEquals(expectedMatrix) }
    }
}