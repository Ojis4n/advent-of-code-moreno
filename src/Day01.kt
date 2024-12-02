import kotlin.math.abs

fun main() {
    fun listPair(input: List<String>): Pair<MutableList<Int>, MutableList<Int>> {
        val list1 = mutableListOf<Int>()
        val list2 = mutableListOf<Int>()

        input.forEach { line ->
            val numbers = line.split("\\s+".toRegex())
            if (numbers.size >= 2) {
                val num1 = numbers[0].toIntOrNull()
                val num2 = numbers[1].toIntOrNull()

                if (num1 != null && num2 != null) {
                    list1.add(num1)
                    list2.add(num2)
                }
            }
        }
        return Pair(list1, list2)
    }

    fun part1(input: List<String>) {
        val (list1, list2) = listPair(input)
        var total = 0
        list1.sort()
        list2.sort()
        for (i in list1.indices) {
            val min1 = list1[i]
            val min2 = list2[i]

            val distance = abs(min1 - min2)

            total += distance

        }

        println("total distance $total")
    }

    fun part2(input: List<String>){
        val (list1, list2) = listPair(input)
        var total = 0

        for (i in list1.indices) {
            for (j in list1.indices) {
                val num1 = list1[i]
                var occurrences = 0
                if(num1 == list2[j]) {
                    occurrences++
                }

                val similarity = num1 * occurrences
                total += similarity
            }

        }

        println("similarity score $total")

    }

    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input)
    part2(input)

}
