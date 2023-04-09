import java.io.File
import kotlin.math.absoluteValue

private val input = File("src/main/kotlin/data_2022_10.txt").readLines()
private val signals: List<Int> = parseInput(input).runningReduce(Int::plus)

fun main() {

    /** Part 1 */
    println(signals.sampleSignals().sum())

    /** Part 2 */
    println(signals.screen().print())


}

private fun parseInput(input: List<String>): List<Int> =
    buildList {
        add(1)
        input.forEach { line ->
            add(0)
            if (line.startsWith("addx")) {
                add(line.substringAfter(" ").toInt())
            }
        }
    }

private fun List<Int>.sampleSignals(): List<Int> =
    (60 .. size step 40).map { cycle ->
        cycle * this[cycle - 1]
    } + this[19] * 20


private fun List<Int>.screen(): List<Boolean> =
    this.mapIndexed { pixel, signal -> (signal-(pixel%40)).absoluteValue <= 1 }


private fun List<Boolean>.print() {
    this.windowed(40, 40, false).forEach { row ->
        row.forEach { pixel ->
            print(if(pixel) '#' else ' ')
        }
        println()
    }
}



