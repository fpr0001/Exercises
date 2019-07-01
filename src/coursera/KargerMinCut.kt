package coursera

import java.io.File
import java.lang.RuntimeException
import kotlin.random.Random
import kotlin.collections.*
import kotlin.math.ceil
import kotlin.math.ln
import kotlin.math.pow

fun main() {

    val matrixFromFile = returnMatrixFromFile("kargerMinCut.txt")
    printMinCut(matrixFromFile)

}

private fun findMinCut(matrix: MutableList<MutableList<Int>>): Int {

    if (matrix.size == 2) {
        return matrix[0].size - 1
    }

    val vertices = matrix[Random.nextInt(0, matrix.size)]
    val randomInt = Random.nextInt(1, vertices.size)
    val value = vertices[randomInt]

    val verticesToBeGone = matrix.find { list -> list.first() == value }
    if (verticesToBeGone == null) {
        throw RuntimeException("Something went wrong")
    } else {
        verticesToBeGone.removeAll { itm -> itm == vertices.first() }
        verticesToBeGone.removeAt(0)
        vertices.addAll(verticesToBeGone)
        matrix.remove(verticesToBeGone)
    }

    vertices.removeAll { it == value }

    matrix.forEach { vrtcs ->
        if (vrtcs != vertices) {
            vrtcs.replaceAll { itm ->
                if (itm == value) {
                    vertices[0]
                } else {
                    itm
                }
            }
        }
    }

    return findMinCut(matrix)
}

private fun printMinCut(originalMatrix: MutableList<MutableList<Int>>) {

    val response = IntArray(getNumberOfTimesToRunFindMinCutAlgorithm(originalMatrix.size))
    for (i in 0 until response.size) {
        val minCutTemp = findMinCut(originalMatrix.getCopy())
        println(minCutTemp)
        response[i] = minCutTemp
    }

    println("\n Mincut = ${response.min()}")
}

fun MutableList<MutableList<Int>>.getCopy(): MutableList<MutableList<Int>> {
    return MutableList(size) { index ->
        val tmp = get(index).toMutableList()
        MutableList(tmp.size) { i -> tmp[i] }
    }
}

/**
 * Because the algorithm is probabilistic, it has to be run multiple times to get an accurate result
 */
private fun getNumberOfTimesToRunFindMinCutAlgorithm(graphSize: Int): Int {
    val response = ceil(ln(graphSize.toDouble()) * (graphSize.toDouble().pow(2)) * 2).toInt()
    println("Number of times to run the algorithm: $response")
    return response
}

/**
 *
 * @param fileName the file name located in the same directory as this file. To use the kargerTestFiles, append
 * the suffix 'kargerTestFiles/' to the name of the file before passing to this function
 *
 * @param delimeter when using the kargerTestFiles, use the following delimeter ' '
 */
private fun returnMatrixFromFile(fileName: String, delimeter: String = "\t"): MutableList<MutableList<Int>> {

    val file = File("/Users/fpr0001/IdeaProjects/Exercises/src/coursera/$fileName")
    return file.useLines { sequence ->
        sequence
                .toList()
                .map {
                    it
                            .split(delimeter)
                            .mapNotNull { numb ->
                                try {
                                    numb.toInt()
                                } catch (e: NumberFormatException) {
                                    null
                                }
                            }
                            .toMutableList()
                }.toMutableList()
    }
}

/**
 * To print the matrix
 */
fun MutableList<MutableList<Int>>.print() {

    fun getSpaces(count: Int): String {
        val response = StringBuilder()
        for (i in 0..count) {
            response.append(" ")
        }
        return response.toString()
    }

    println("\n\n")
    forEachIndexed { i, list ->
        list.forEachIndexed { index, value ->
            print(when {
                i == 0 -> "$value${getSpaces(4 - index.toString().length)}"
                index == 0 -> "$value${getSpaces(4 - i.toString().length)}"
                else -> "$value${getSpaces(4 - 1)}"
            })
        }
        println()
    }
}