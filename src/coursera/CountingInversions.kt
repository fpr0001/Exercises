package coursera


fun main() {
    val b = findNumberOfInversions(getIntArrayFromFile("arrayInversions.txt"))
    print("Sorted array: ")
    b.first.map { print("$it, ") }
    println()
    println("Number of inversions: ${b.second}")
}

fun findNumberOfInversions(array: IntArray, inversionCount: String = "0"): Pair<IntArray, String> {
    if (array.size == 1) {
        return Pair(array, inversionCount)
    }
    val (left, leftCount) = findNumberOfInversions(array.sliceArray(IntRange(0, (array.size / 2) - 1)))
    val (right, rightCount) = findNumberOfInversions(array.sliceArray(IntRange(left.size, array.size - 1)))

    var j = 0
    var i = 0
    val response = IntArray(array.size)
    var responseCount = "0"
    for (k in 0 until array.size) {

        if (j == right.size) { //right array is "empty"
            response[k] = left[i]
            i++
            continue
        }
        if (i == left.size) { //left array is "empty"
            response[k] = right[j]
            j++
            continue
        }

        val b = left[i]
        val c = right[j]

        if (b < c) {
            response[k] = b
            i++
        } else {
            response[k] = c
            responseCount = bigSumOf(responseCount, (left.size - i).toString())
            j++
        }
    }

    return Pair(response, bigSumOf(responseCount, leftCount, rightCount))
}