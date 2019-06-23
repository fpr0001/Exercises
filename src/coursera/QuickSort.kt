package coursera

fun main() {
    val intArray = intArrayOf(3, 8, 2, 5, 1, 4, 7, 6)
    intArray.quickSort()
    intArray.map { print("$it, ") }
}

fun IntArray.quickSort(intRange: IntRange = 0 until size) {

    if (intRange.last - intRange.first == 0) { //array is sorted
        return
    }

    val pivotIndex = getPivotIndex(intRange)

    val intRanges = partitionArray(pivotIndex, intRange)

    quickSort(intRanges.first)
    quickSort(intRanges.second)

}

/**
 * @return index leftmost and rightmost index of
 */
fun IntArray.partitionArray(pi: Int, intRange: IntRange): Pair<IntRange, IntRange> {

    //if pivot is not the first one in the array, swap the integers so pivot be in the first position (but not for now)

    val pivot = get(pi)
    var pivotIndex = pi

    if (pi != intRange.first) {
        swap(intRange.first, pi)
        pivotIndex = intRange.first
    }

    var i = pivotIndex + 1 //divides what's greater than pivot

    for (j: Int in intRange) {

        val auxJ = get(j)

        if (auxJ < pivot) {
            swap(i, j)
            i++
        }
    }

    swap(pivotIndex, i - 1)

    val intRage1: IntRange
    intRage1 = if (intRange.first == i - 1) { //pivot is in the first place
        IntRange(i, intRange.last)
    } else {
        IntRange(intRange.first, i - 2)
    }
    val intRange2 = if (i > intRange.last) {
        IntRange(intRange.last, intRange.last)
    } else {
        IntRange(i, intRange.last)
    }
    return Pair(intRage1, intRange2)
}

fun IntArray.swap(firstIndex: Int, lastIndex: Int) {
    if (firstIndex == lastIndex) return
    val firstInt = get(firstIndex)
    set(firstIndex, get(lastIndex))
    set(lastIndex, firstInt)
}

/**
 * Chooses pivot and return its index
 */
fun getPivotIndex(intRange: IntRange): Int {
    return intRange.first
}