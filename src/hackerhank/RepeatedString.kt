package hackerhank

fun main() {
    println(repeatedString("aba",12))
}


/**
 * Function counts how many 'a' there are when s is repeated until reaches n length
 * If s = "aba" and n = 5 then response is 3 because resulting string is 'abaab'
 * If s = "aba" and n = 10 then response is 7 because resulting string is 'abaabaabaa'
 */
fun repeatedString(s: String, n: Long): Long {
    var countA: Long
    val aaa = s.count { it == 'a' }
    countA = aaa * (n / s.length)
    for (i in 0 until n.rem(s.length)) {
        if (s[i.toInt()] == 'a') {
            countA++
        }
    }
    return countA
}