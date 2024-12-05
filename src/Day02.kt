import kotlin.math.abs

fun main() {

    fun levelDiffer(difference: Int): Boolean {
        return abs(difference) !in 1..3
    }

    fun verificationLevels(array: IntArray, decreasing: Boolean, increasing: Boolean): Boolean {
        var decreasingLevel = decreasing
        var increasingLevel = increasing
        var stillValid = 0
        for (i in 0 until array.toList().size - 1) {
            val difference = array[i + 1] - array[i]

            when {
                difference > 0 -> {
                    stillValid ++
                    if (decreasingLevel) decreasingLevel = false
                    if (!increasingLevel || levelDiffer(difference)) {
                        return false
                    }
                }

                difference < 0 -> {
                    stillValid ++
                    if (increasingLevel) increasingLevel = false
                    if (!decreasingLevel || levelDiffer(difference)) {
                        return false
                    }
                }

                else -> return false
            }
        }
        return true
    }

    fun safetyValidation(array: IntArray, decreasing: Boolean, increasing: Boolean): Boolean {
        return verificationLevels(array, decreasing, increasing)
    }

    fun isSafeReport(array: IntArray) :Boolean{
        val increasing = true
        val decreasing = true
        return safetyValidation(array, decreasing, increasing)
    }

    fun part1(input: List<String>) {
        val listOfArrays = mutableListOf<IntArray>()
        var safe = 0
        var notSafe = 0

        input.forEach { line ->
            val numbersArray = line.split("\\s+".toRegex())
                .mapNotNull { it.toIntOrNull() }
                .toIntArray()

            listOfArrays.add(numbersArray)
        }
        listOfArrays.forEachIndexed {
            index, array ->
            println("$index: ${array.toList()} ${if (isSafeReport(array)) "SAFE" else "UNSAFE"}")
            if (isSafeReport(array)) {
                safe++
            } else {
                notSafe++
            }
        }
        println("safe reports $safe || notSafe reports $notSafe")
    }

    fun dampenerCheck(array: IntArray) : Int{
        var stillValid = 0
        for (i in 0 until array.toList().size) {
            val subarray = array.toMutableList().apply {
                removeAt(i)
            }.toIntArray()
            if (isSafeReport(subarray)) {
                stillValid++
            }
        }
        return stillValid
    }

    fun part2(input: List<String>){
        val listOfArrays = mutableListOf<IntArray>()
        var safe = 0
        var notSafe = 0

        input.forEach { line ->
            val numbersArray = line.split("\\s+".toRegex())
                .mapNotNull { it.toIntOrNull() }
                .toIntArray()

            listOfArrays.add(numbersArray)
        }
        listOfArrays.forEachIndexed {
            index, array ->
            if (isSafeReport(array)) {
                println("$index: ${array.toList()} SAFE")
                safe++
            } else {
                if (abs(dampenerCheck(array)) in 1..2) {
                    println("$index: ${array.toList()} SAFE")
                    safe++
                } else {
                    println("$index: ${array.toList()} NOT SAFE")
                    notSafe++
                }
            }
        }

        println("safe reports $safe || notSafe reports $notSafe")
    }

    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day02_test")
//    check(part1(testInput) == 1)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
//    part1(input)
    part2(input)

}
