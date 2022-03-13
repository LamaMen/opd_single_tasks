package matrix

class MinLineProjection : LineProjectionStrategy {
    override fun projectLine(line: IntArray): Int {
        var minElement = Int.MAX_VALUE
        for (elem in line) {
            if (elem < minElement) {
                minElement = elem
            }
        }
        return minElement
    }
}
