import java.io.File

fun main() {

    val input = File("src/main/kotlin/data_2022_07.txt").readLines()

    val rootDirectory: Directory = parseInput(input)

    /** Part 1 */
    println(rootDirectory.find { it.size <= 100_000 }.sumOf { it.size })

    /** Part 2 */

}



class Directory(val name: String) {
    private val subDirs: MutableMap<String, Directory> = mutableMapOf()
    private var sizeOfFiles: Int = 0

    val size: Int
        get() = sizeOfFiles + subDirs.values.sumOf { it.size }

    fun addFile(size: Int) {
        sizeOfFiles += size
    }

    fun traverse(dir: String): Directory =
        subDirs.getOrPut(dir) { Directory(dir) }

    fun find(predicate: (Directory) -> Boolean): List<Directory> =
        subDirs.values.filter(predicate) +
                subDirs.values.flatMap { it.find(predicate) }
}

private fun parseInput(input: List<String>): Directory {
    val callStack = ArrayDeque<Directory>().apply { add(Directory("/")) }
    input.forEach { item ->
        when {
            item == "$ ls" -> {}  // Noop
            item.startsWith("dir") -> {} // Noop
            item == "$ cd /" -> callStack.removeIf { it.name != "/" }
            item == "$ cd .." -> callStack.removeFirst()
            item.startsWith("$ cd") -> {
                val name = item.substringAfterLast(" ")
                callStack.addFirst(callStack.first().traverse(name))
            }
            else -> {
                val size = item.substringBefore(" ").toInt()
                callStack.first().addFile(size)
            }
        }
    }
    return callStack.last()
}

