import java.io.File

/** 2022 - Day 2:
 * Rock, Paper, Scissors
 Part 1 -  What would your total score be if everything goes exactly according to your strategy guide?
 Part 2 -  What would your total score be if everything goes exactly according to your strategy guide?
 */

fun main() {
    val data = File("src/main/kotlin/data_2022_02.txt").readLines()
    println(scorePerRound_Part1(data))
    println(scorePerRound_part2(data))
    println(scorePerRound2(data))
}


fun scorePerRound_Part1 (res: List<String>): Int {
    var sum : Int = 0

    for (s in res) {
        if (s.equals("A X")) {sum += 4}
        if (s.equals("A Y")) {sum += 8}
        if (s.equals("A Z")) {sum += 3}
        if (s.equals("B X")) {sum += 1}
        if (s.equals("B Y")) {sum += 5}
        if (s.equals("B Z")) {sum += 9}
        if (s.equals("C X")) {sum += 7}
        if (s.equals("C Y")) {sum += 2}
        if (s.equals("C Z")) {sum += 6}
}
    return sum
}

fun scorePerRound_part2 (res: List<String>): Int {
    var sum : Int = 0

    for (s in res) {
        if (s.equals("A X")) {sum += 3}
        if (s.equals("A Y")) {sum += 4}
        if (s.equals("A Z")) {sum += 8}
        if (s.equals("B X")) {sum += 1}
        if (s.equals("B Y")) {sum += 5}
        if (s.equals("B Z")) {sum += 9}
        if (s.equals("C X")) {sum += 2}
        if (s.equals("C Y")) {sum += 6}
        if (s.equals("C Z")) {sum += 7}
    }
    return sum
}

// Efter googling, kan appliceras även på part 2, men tveksamt om det optimerar

fun scorePerRound2(res: List<String>): Int {
    return res.sumOf {
        when (it) {
            "A X" -> 1 + 3
            "A Y" -> 2 + 6
            "A Z" -> 3 + 0
            "B X" -> 1 + 0
            "B Y" -> 2 + 3
            "B Z" -> 3 + 6
            "C X" -> 1 + 6
            "C Y" -> 2 + 0
            "C Z" -> 3 + 3
            else -> error("input error")
        }.toInt()
    }
}

