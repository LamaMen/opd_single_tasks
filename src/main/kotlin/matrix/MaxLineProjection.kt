package matrix

class MaxLineProjection : LineProjectionStrategy {
    override fun projectLine(line: IntArray): Int {
        var maxElement = Int.MIN_VALUE
        for (elem in line) {
            if (elem > maxElement) {
                maxElement = elem
            }
        }
        return maxElement
    }
}
