package coursera

fun main() {
    val a = intArrayOf(6, 3, 2, 8, 4, 9, 8, 10, 3, 11, 15, 7)
    mergeSort(a).map { print("$it, ") }
}

fun mergeSort(array: IntArray): IntArray {
    if (array.size == 1) {
        return array
    }
    val left = mergeSort(array.sliceArray(IntRange(0, (array.size / 2) - 1)))
    val right = mergeSort(array.sliceArray(IntRange(left.size, array.size - 1)))

    var j = 0
    var i = 0
    val response = IntArray(array.size)
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
            j++
        }
    }
    return response
}