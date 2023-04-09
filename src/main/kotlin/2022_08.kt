import java.io.File

private val input = File("src/main/kotlin/data_2022_08.txt").readLines()
private val grid: Array<IntArray> = parseInput(input)
private val gridHeight: Int = grid.size
private val gridWidth: Int = grid.first().size

fun main() {

    /** Part 1 */
    println(solvePart1())

    /** Part 2 */
    println(solvePart2())

}


private fun parseInput(input: List<String>): Array<IntArray> =
    input.map { row -> row.map(Char::digitToInt).toIntArray() }
        .toTypedArray()


private fun Array<IntArray>.viewFrom(x: Int, y: Int): List<List<Int>> =
    listOf(
        (y - 1 downTo 0).map { this[it][x] }, // Up
        (y + 1 until gridHeight).map { this[it][x] }, // Down
        this[y].take(x).asReversed(), // Left
        this[y].drop(x + 1) // Right
    )

private fun Array<IntArray>.isVisible(x: Int, y: Int): Boolean =
    viewFrom(x, y)
        .any { direction -> direction.all { it < this[y][x] } }

fun solvePart1(): Int = (1 until gridHeight - 1)
    .sumOf { y -> (1 until gridWidth - 1).count { x -> grid.isVisible(x, y) } } +
        (2 * gridHeight) + (2 * gridWidth) - 4


/** Part 2 */

inline fun <T> Iterable<T>.takeUntil(predicate: (T) -> Boolean): List<T> {
    val list = ArrayList<T>()
    for (item in this) {
        list.add(item)
        if (predicate(item))
            break
    }
    return list
}

fun Iterable<Int>.product(): Int = reduce { a, b -> a * b }

private fun Array<IntArray>.scoreAt(x: Int, y: Int): Int =
    viewFrom(x, y)
        .map { direction -> direction.takeUntil { it >= this[y][x] }.count() }
        .product()

fun solvePart2(): Int = (1 until gridHeight - 1)
    .maxOf { y -> (1 until gridWidth - 1).maxOf { x -> grid.scoreAt(x, y) } }