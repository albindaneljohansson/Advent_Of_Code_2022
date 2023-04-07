import java.io.File

/** 2022 - Day 5: * Rearrange stacks
 Part 1 -  After the rearrangement procedure completes, what crate ends up on top of each stack? */

val data = File("src/main/kotlin/data_2022_05.txt").readLines()

    fun main() {

        val part1 = Day05(data)
        println(part1.solvePart1())
        val part2 = Day05(data)
        println(part2.solvePart2())
    }

class Day05(input: List<String>) {

    private val stacks: List<MutableList<Char>> = createStacks(input)
    private val instructions: List<Instruction> = parseInstructions(input)

    private fun createStacks(input: List<String>): List<MutableList<Char>> {
        val stackRows = input.takeWhile { it.contains('[') }
        return (1..stackRows.last().length step 4).map { index ->
            stackRows
                .mapNotNull { it.getOrNull(index) }
                .filter { it.isUpperCase() }
                .toMutableList()
        }
    }


    private data class Instruction(val amount: Int, val source: Int, val target: Int)

    private fun parseInstructions(input: List<String>): List<Instruction> = input
            .dropWhile { !it.startsWith("move") }
            .map { row ->
                row.split(" ").let { parts ->
                    Instruction(parts[1].toInt(), parts[3].toInt() - 1, parts[5].toInt() - 1)
                }
            }

    private fun Iterable<Iterable<Char>>.tops(): String = map { it.first() }.joinToString("")

    private fun performInstructions(reverse: Boolean) {
        instructions.forEach { (amount, source, destination) ->
            val toBeMoved = stacks[source].take(amount)
            repeat(amount) { stacks[source].removeFirst() }
            stacks[destination].addAll(0, if (reverse) toBeMoved.reversed() else toBeMoved)
        }
    }

    fun solvePart1(): String {
        performInstructions(true)
        return stacks.tops()
    }

    fun solvePart2(): String {
        performInstructions(false)
        return stacks.tops()
    }
}
