package matrix

fun sortingMatrix() {
    val matrix = arrayOf(
        intArrayOf(1, 1, 3),
        intArrayOf(2, 2, 2),
        intArrayOf(0, 0, 0),
    )

    while (true) {
        println("Исходная матрица:")
        printMatrix(matrix)
        println("Выбирите способ сортировки:")
        println("1 - В порядке возрастания сумм элементов строк матрицы")
        println("2 - В порядке убывания сумм элементов строк матрицы")
        println("3 - По возрастанию максимального элемента в строке матрицы")
        println("4 - По убыванию максимального элемента в строке матрицы")
        println("5 - В порядке возрастания минимального элемента в строке матрицы")
        println("6 - В порядке убывания минимального элемента в строке матрицы")

        val strategyIndex = readLine()!!.toIntOrNull() ?: break
        val strategy: LineProjectionStrategy = when ((strategyIndex + 1) / 2) {
            1 -> SumProjection()
            2 -> MaxLineProjection()
            3 -> MinLineProjection()
            else -> null
        } ?: break

        val sorter = MatrixSorter(strategy)
        val sorted = sorter(matrix, reversed = (strategyIndex + 1) % 2 == 1)
        printMatrix(sorted)

        println()
        println()
        println()
    }
}

fun printMatrix(matrix: Matrix) {
    for (line in matrix) {
        for (elem in line) {
            print("$elem ")
        }
        println()
    }
}