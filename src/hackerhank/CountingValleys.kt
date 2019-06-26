package hackerhank

/**
 * @param n is the length of [s]
 * @param s is string containing lots of D and U chars.
 *
 * Person starts at sea level. Each D means a downwards pace. Each U means an upwards pace. When person
 * comes from a level below the sea to the surface, that counts as a valley
 *
 * @return number of valleys the person walked out from
 */
fun countingValleys(n: Int, s: String): Int {
    var countValleys = 0
    var countD = 0
    var countU = 0
    for (c in s) {
        if (c == 'D') {
            countD++
        } else {
            countU++
            if (countD == countU) {
                countValleys++
            }
        }
    }
    return countValleys
}