package matrix


typealias Matrix = Array<IntArray>

fun Matrix.isEquals(o: Matrix): Boolean {
    if (this.size != o.size) return false
    for (i in this.indices) {
        if (this[i].size != o[i].size) return false
        for (j in this[i].indices) {
            if (this[i][j] != o[i][j]) return false
        }
    }
    return true
}

fun Matrix.swapLines(i: Int, j: Int) {
    val tempLine = this[i]
    this[i] = this[j]
    this[j] = tempLine
}