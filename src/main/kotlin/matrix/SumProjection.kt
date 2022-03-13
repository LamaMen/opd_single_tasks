package matrix

class SumProjection : LineProjectionStrategy {
    override fun projectLine(line: IntArray): Int {
        var sum = 0
        for (elem in line) {
            sum += elem
        }
        return sum
    }
}
