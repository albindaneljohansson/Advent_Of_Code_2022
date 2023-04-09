import java.io.File

fun main() {

    val input = File("src/main/kotlin/data_2022_06.txt").readText()

    /** Part 1 */
    println(input.getStartIndex(4))

    /** Part 2 */
    println(input.getStartIndex(14))

}

/** Extension function - withIndex returns value & index - get last index+1 where toSet-size = input*/

private fun String.getStartIndex(sequenceSize: Int): Int =
    withIndex()
        .windowed(sequenceSize, 1)
        .first { window -> window.map { it.value }.toSet().size == sequenceSize }
        .last().index +1