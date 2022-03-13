package matrix

class MatrixSorter(private val projectionStrategy: LineProjectionStrategy) {
    operator fun invoke(sourceMatrix: Matrix, reversed: Boolean = false): Matrix {
        val sortedMatrix = sourceMatrix.clone()

        for (i in sortedMatrix.indices) {
            var j = i
            while (j > 0 && sortedMatrix[j - 1].isBigger(sortedMatrix[j], reversed)) {
                sortedMatrix.swapLines(j - 1, j)
                j--
            }
        }

        return sortedMatrix
    }

    private fun IntArray.isBigger(
        other: IntArray,
        reversed: Boolean,
    ): Boolean {
        val projectedLine1 = projectionStrategy.projectLine(this)
        val projectedLine2 = projectionStrategy.projectLine(other)

        return if (reversed) {
            projectedLine1 < projectedLine2
        } else {
            projectedLine1 > projectedLine2
        }
    }
}
