import java.io.File

fun main() {

    val input = File("src/main/kotlin/data_2022_06.txt").readText()

    /** Part 1 */
    println(input.findStartMarker(4))

    /** Part 2 */
    println(input.findStartMarker(14))

}

/** Extension function */
private fun String.findStartMarker(startMarkerSize: Int): Int =
    withIndex()
        .windowed(startMarkerSize, 1)
        .first { window ->
            window.map { it.value }.toSet().size == startMarkerSize
        }
        .last().index +1