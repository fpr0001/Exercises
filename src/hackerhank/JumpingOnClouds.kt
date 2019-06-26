package hackerhank

/**
 * She can jump on any cumulus cloud having a number that is equal to the number of the current cloud plus 1 or 2.
 * For each game, Emma will get an array of clouds numbered 0 if they are safe or 1 if they must be avoided.
 * For example, c= [0,1,0,0,0,1,0]. She could follow the following two paths of indexes: 0,2,4,6 or 0,2,3,4,6.
 * The first path takes 3 jumps while the second takes 4, so the answer to this example would be 3.
 */
fun jumpingOnClouds(c: Array<Int>): Int {
    var jumps = 0
    var i = 0
    while (i < c.size) {
        if (i + 2 < c.size) {
            if (c[i + 2] == 0) {
                jumps++
                i += 2
            } else {
                jumps++
                i++
            }
        } else if (i + 1 < c.size) {
            jumps++
            i++
        } else {
            break
        }
    }
    Int.MAX_VALUE
    return jumps
}

