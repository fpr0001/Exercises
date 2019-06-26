package hackerhank

import kotlin.collections.*

/**
 * @param n is the size of the array
 * @param ar is the array containing Ints. For every pair of Int, we count 1 pair of socks.
 *
 * @return the number of pair of socks
 */
fun sockMerchant(n: Int, ar: Array<Int>): Int {
    val hm = HashMap<Int, Int>()
    hm[-1] = 0
    for (i in ar.indices) {
        val num = ar[i]
        if (hm[num] == null) {
            hm[num] = 1
        } else {
            val temp = hm[num]!! + 1
            hm[num] = temp
            if (temp.rem(2) == 0) {
                hm[-1] = hm[-1]!! + 1
            }
        }
    }
    return hm[-1]!!
}
