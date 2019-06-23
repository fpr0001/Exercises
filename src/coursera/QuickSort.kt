package coursera

var count = 0

fun main() {
    val intArray = getIntArrayFromFile("QuickSort.txt")
    count = 0
    intArray.quickSort()
    intArray.map { print("$it, ") }
    println("\nTotal count: $count")
}

fun IntArray.quickSort(intRange: IntRange = 0 until size) {

    if (intRange.last - intRange.first == 0) { //array is sorted
        return
    }

    count += intRange.last - intRange.first

    val pivotIndex = getPivotIndex(intRange)

    val intRanges = partitionArray(pivotIndex, intRange)

    quickSort(intRanges.first)
    quickSort(intRanges.second)

}

/**
 * @return index leftmost and rightmost index of
 */
fun IntArray.partitionArray(pi: Int, intRange: IntRange): Pair<IntRange, IntRange> {

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
        IntRange(0, 0)
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
fun IntArray.getPivotIndex(intRange: IntRange): Int {

    val first = get(intRange.first)
    val size = intRange.last - intRange.first + 1
    val secondIndex: Int = if (size.rem(2) != 0) {//is odd
        (size / 2)
    } else {
        (size / 2) - 1
    } + intRange.first
    val second = get(secondIndex)
    val third = get(intRange.last)
    val response = when {
        first < second && second < third -> secondIndex
        third < second && second < first -> secondIndex
        second < first && first < third -> intRange.first
        third < first && first < second -> intRange.first
        second < third && third < first -> intRange.last
        first < third && third < second -> intRange.last
        else -> intRange.first
    }

    return response
}