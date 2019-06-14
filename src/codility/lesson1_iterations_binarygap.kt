// https://app.codility.com/programmers/lessons/1-iterations/binary_gap/

import kotlin.collections.*

fun main() {
    println(solution(5004))
}

fun solution(N: Int): Int {
    val modArray = getModArray(MutableArray(), N)
    return getMaxGap(modArray.array)
}

fun getMaxGap(array: ByteArray):Int {
    var maxZeroCount = 0
    var zeroCount = 0
    var began = false
    for (i in array.indices) {
        val byte = array[i]
        if(byte.equals(1.toByte())) {
            if(began) {
               if (zeroCount > maxZeroCount) {
                    maxZeroCount = zeroCount
               }
                zeroCount = 0
            } else {
                began = true
            }
        } else {
            if(began) {
                zeroCount++
            }
        }
    }
    return maxZeroCount
}

class MutableArray constructor(var array: ByteArray = ByteArray(0)) {
    fun add(value: Byte) : MutableArray {
        val arraySize = array.size
        val response = ByteArray(arraySize+1) {
            if(arraySize == it) {
              value
            } else {
                array[it]
            }
        }
        array = response
        return this
    }
}

fun getModArray(mutableMod: MutableArray, intValue: Int): MutableArray {
    val valueDividedByTwo: Int = intValue / 2
    return if (valueDividedByTwo == 0) { //its the last one
        mutableMod.add(intValue.rem(2).toByte())
        mutableMod
    } else {
        mutableMod.add(intValue.rem(2).toByte())
        getModArray(mutableMod, valueDividedByTwo)
    }
}
