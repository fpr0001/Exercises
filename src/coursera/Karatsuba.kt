package coursera

import java.lang.Math.max
import java.math.BigInteger

fun main() {

    //Two 64-digit integers
    val first = "3141592653589793238462643383279502884197169399375105820974944592"
    val second = "2718281828459045235360287471352662497757247093699959574966967627"

    val a = BigInteger(first)
    val b = BigInteger(second)

    println("Multiplication using BigIntegers:                           " + a.multiply(b))
    println("Multiplication using Karatsuba's algorithm through Strings: " + multiplyInts(first, second))
}

/**
 * Multiply two integers using Karatsuba's algorithm
 */
fun multiplyInts(x: String, y: String): String {

    if (x.length <= 3 || y.length <= 3) {
        return x.toInt().times(y.toInt()).toString()
    } else {

        val a = x.take((x.length + 1) / 2)
        val b = x.substring(a.length)
        val c = y.take((y.length + 1) / 2)
        val d = y.substring(c.length)
        val length: Int = max(x.length, y.length) / 2

        val z2 = multiplyInts(a, c)
        val z0 = multiplyInts(b, d)
        val z1 = sumTwoIntegerStrings(multiplyInts(a, d), multiplyInts(b, c))

        val stringAux1 = StringBuilder(z2)
        for (i in 1..length * 2) {
            stringAux1.append("0")
        }

        val stringAux2 = StringBuilder(z1)
        for (i in 1..length) {
            stringAux2.append("0")
        }

        val result = bigSumOf(stringAux1.toString(), stringAux2.toString(), z0)
        return result
    }
}