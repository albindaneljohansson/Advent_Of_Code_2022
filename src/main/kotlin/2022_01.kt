import java.io.File

/** 2022 - Day 1:
 *
 Part 1 -  Find the Elf carrying the most Calories. How many total Calories is that Elf carrying?
 Part 2 - Find the top three Elves carrying the most Calories. How many Calories are those Elves carrying in total?
 */


fun main() {

    //  Lösning 2- Readtext ist - returnerar String
    val calList2 = File("src/Inlamning_02/data_2022_01.txt").readText()
    println(sumOfCalories2(calList2).max())
    // Lösning 2 - Part 2
    println(sumOfCalories2(calList2).take(3).sum())



}

// Lösning 1 - Part1
fun sumOfTopCalories(l: List<String>): List<Int> {
    val calSumList = mutableListOf<Int>()
    var tempsum = 0
    for (c in l) {
        if(c.equals("") ){
            calSumList.add(tempsum)
            tempsum = 0
        } else tempsum += c.toInt() }
    calSumList.add(l.get(l.lastIndex).toInt())
    return calSumList
}

// Lösning 1 - Part2
fun sumOfTopThree(l: List<Int>): Int = l.sorted().reversed().slice(0..2).sum()


// Lösning 2 - Part 1
fun sumOfCalories2 (s: String) =  s.trim().split("\n\n")
        .map { n -> n.lines().sumOf {it.toInt()} }.sortedDescending()




