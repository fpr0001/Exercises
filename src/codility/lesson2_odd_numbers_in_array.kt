//https://app.codility.com/programmers/lessons/2-arrays/odd_occurrences_in_array/

// you can also use imports, for example:
// import kotlin.math.*

// you can write to stdout for debugging purposes, e.g.
// println("this is a debug message")

fun main() {
    println(solution(intArrayOf(23, 1, 2, 3, 4, 5, 6, 7)))
}

fun solution(A: IntArray): Int {
    // write your code in Kotlin
    if (A.size == 1) return A[0]

    A.sortDescending()
    val maxIndex = A.size - 1
    for (i in 1..maxIndex step 2) {
        val lastInt: Int = A[i - 1]
        val currentInt = A[i]
        if (lastInt != currentInt) {
            return lastInt
        }
    }
    return A[A.size - 1]
}
