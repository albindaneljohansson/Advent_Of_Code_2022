import java.io.File

/** 2022 - Day 3:
 * Rucksack w/ two compartments
 Part 1 -  Find the item type that appears in both compartments of each rucksack. What is the sum of the priorities of those item types?
 Part 2 -  Find the item type that corresponds to the badges of each three-Elf group. What is the sum of the priorities of those item types?
 */

fun main() {

    val data = File("src/main/kotlin/data_2022_03.txt").readLines()

    // Lösning 1
    println(Part1(data))
    println(Part1_2(data))
    // Lösning 2
    println(Part2(data))
    println(Part2_2(data))
}


// Lösning 2:

fun Part1_2 (l: List<String>): Int =
        l.sumOf{(listOf(it.substring(0..it.length/2), it.substring(it.length/2))
                .map{it.toSet()}.reduce {a, b -> a intersect b}.first().prio())}

// Extension function
fun Char.prio(): Int =
        if(this.isUpperCase()){ this.code - 'A'.code + 27}
        else { this.code - 'a'.code + 1}

// Efter googling -> chunked
fun Part2_2(l: List<String>): Int  =
        l.chunked(3).sumOf{it.map {it.toSet()}.reduce{a, b -> a intersect b}.first().prio()}



// Lösning 1 - 3 loopar

fun Part1 (l: List<String>): Int {
    val alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()
    var result = mutableListOf<Int>()
    var prioritySum = 0
    for (s in l) {
        var tempLength = s.length
        var subString1 = s.substring(0,tempLength/2).toCharArray()
        var subString2 = s.substring(tempLength/2, tempLength).toCharArray()
        for (c in subString1) {
                if (subString2.contains(c)) {
                    var priority = 0
                    for (a in alphabet) {
                        priority++
                        if (a == c) {
                            prioritySum = priority
                        }
                    }
                }
        }
        result.add(prioritySum)
        prioritySum = 0
    }
return result.sum()
}


fun Part2(l: List<String>): Int {
    var sum = 0
    for (i in 0..l.size-1 step 3) {
        var l = listOf(l.get(i), l.get(i + 1), l.get(i + 2))
        sum += l.map { it.toSet() }.reduce { a, b -> a intersect b}.first().prio()
    }
    return sum
}
