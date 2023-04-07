import java.io.File

/** 2022 - Day 4:
 * Overlapping assignments
 Part 1 - In how many assignment pairs does one range fully contain the other?
 Part 2 - In how many assignment pairs do the ranges overlap?
 */

fun main() {

    val d = File("src/main/kotlin/data_2022_04.txt").readLines()
    // Lösning 1
    println(overLapSum(d))
    println(anyOverLapSum(d))

    // Lösning 2
    println(d.overLapSum2())
    println(d.overLapSum3())
}

// Lösning 2 - Funktionellt men kanske inte bättre
// Part 1
fun boolCount (l: List<Pair<IntRange,IntRange>>): Int =
        l.count { it.first fullOverlap it.second || it.second fullOverlap it.first  }

fun String.intRange(): IntRange = substringBefore("-").toInt() .. substringAfter("-").toInt()

fun List<String>.overLapSum2(): Int = boolCount(this.map {it.substringBefore(",").intRange() to it.substringAfter(",").intRange()})

infix fun IntRange.fullOverlap(other: IntRange): Boolean = first <= other.first && last >= other.last


// Lösning 2 - Part 2
fun List<String>.overLapSum3(): Int = boolCount2(this.map {it.substringBefore(",").intRange() to it.substringAfter(",").intRange()})

infix fun IntRange.anyOverlap2(other: IntRange): Boolean = first <= other.last && other.first <= last

fun boolCount2 (l: List<Pair<IntRange,IntRange>>): Int = l.count { it.first anyOverlap2  it.second }



// Lösning 1 - part 1:
fun overLapSum (l: List<String>): Int = l.count { e -> e.overLap() }

// Extension function
fun String.overLap(): Boolean {
    val l = this.split(',').map { it.split('-') }

    val r1 =  Integer.parseInt(l.get(0).get(0))
    val r2 =  Integer.parseInt(l.get(0).get(1))
    val r3 =  Integer.parseInt(l.get(1).get(0))
    val r4 =  Integer.parseInt(l.get(1).get(1))

    return ((r1 in r3..r4 && r2 in r3..r4) || (r3 in r1..r2 && r4 in r1..r2))
}

// Lösning 1 - part 2:

fun anyOverLapSum (l: List<String>): Int = l.count { e -> e.anyOverlap() }

// Extension function
fun String.anyOverlap(): Boolean {
    val l = this.split(',').map { it.split('-') }

    val r1 =  Integer.parseInt(l.get(0).get(0))
    val r2 =  Integer.parseInt(l.get(0).get(1))
    val r3 =  Integer.parseInt(l.get(1).get(0))
    val r4 =  Integer.parseInt(l.get(1).get(1))

    return ((r1 in r3..r4 || r2 in r3..r4) || (r3 in r1..r2 || r4 in r1..r2))
}




