package matrix

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class LineProjectionStrategyTest {
    private val sourceLine = intArrayOf(1, 2, 3)

    @Test
    fun `can project by sum elements`() {
        val lineProjectionStrategy: LineProjectionStrategy = SumProjection()

        val actual = lineProjectionStrategy.projectLine(sourceLine)

        assertEquals(6, actual)
    }

    @Test
    fun `can project by max element in line`() {
        val lineProjectionStrategy: LineProjectionStrategy = MaxLineProjection()

        val actual = lineProjectionStrategy.projectLine(sourceLine)

        assertEquals(3, actual)
    }

    @Test
    fun `can project by min element in line`() {
        val lineProjectionStrategy: LineProjectionStrategy = MinLineProjection()

        val actual = lineProjectionStrategy.projectLine(sourceLine)

        assertEquals(1, actual)
    }
}