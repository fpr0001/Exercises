package coursera

/**
 * Used to sum two huge integers
 */
fun sumTwoIntegerStrings(str1: String, str2: String): String {
    if (str1.length <= 4 && str2.length <= 4) {
        return str1.toInt().plus(str2.toInt()).toString()
    }

    val intArray1 = str1.toCharArray().map { it.toString().toInt() }
    val intArray2 = str2.toCharArray().map { it.toString().toInt() }

    val biggerArray = if (intArray1.size > intArray2.size) intArray1 else intArray2
    val smallerArray = if (biggerArray == intArray1) intArray2 else intArray1

    // [1, 2, 3, 4, 5, 6, 7, 8]
    //       [5, 5, 5, 9, 6, 5]

    val response = StringBuilder()
    val deltaSizes = biggerArray.size - smallerArray.size
    var plus = 0
    for (i in (biggerArray.size - 1) downTo 0) {
        val bigger = biggerArray[i]
        var sum: Int
        sum = if (i - deltaSizes < 0) {
            bigger + plus
        } else {
            val smaller = smallerArray[i - deltaSizes]
            bigger + smaller + plus
        }
        plus = sum / 10
        val remaining = sum.rem(10)
        response.append(remaining)
    }
    if (plus > 0) {
        response.append(plus)
    }

    return response.reversed().toString()
}

/**
 * Calls [sumTwoIntegerStrings] in a for loop
 */
fun bigSumOf(vararg bigIntegers: String) : String {
    var result = "0"
    for (nextString: String in bigIntegers) {
        result = sumTwoIntegerStrings(result, nextString)
    }
    return result
}