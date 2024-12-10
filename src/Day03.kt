fun main() {


    fun mulNumber(input: List<String>): List<Int> {
        val regex = Regex("""mul\((\d{1,3}),(\d{1,3})\)""")

        return input
            .flatMap { line ->
            regex.findAll(line).map { results ->
                val num1 = results.groupValues[1].toInt()
                val num2 = results.groupValues[2].toInt()

                num1 * num2
            }
        }
    }

    fun removeDisabled(it: String) = it.replace("don't\\(\\).*?do\\(\\)|don't\\(\\).*?\$|don't\\(\\).*?don't\\(\\)".toRegex(), "")

    fun part1(input: List<String>) {
        val total = mulNumber(input).sum()
        println(total)

    }

    fun part2(input: List<String>){
        input.joinToString(separator = "").println()
        val enabledPart = listOf(removeDisabled(input.joinToString(separator = "")))
        return part1(enabledPart)

    }


    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day03_test")
//    check(part1(testInput) == 1)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day03")
    part1(input)
    part2(input)
}

