package coursera

import java.io.File
import kotlin.math.max
import kotlin.random.Random
import kotlin.collections.*

fun main() {

//    val graph = buildGraphMatrix(returnMatrixFromFile())

    val line1 = mutableListOf(0,1,2,3,4,5,6)
    val line2 = mutableListOf(1,0,1,1,0,0,0)
    val line3 = mutableListOf(2,0,0,1,0,0,0)
    val line4 = mutableListOf(3,0,0,0,1,0,0)
    val line5 = mutableListOf(4,0,0,0,0,1,1)
    val line6 = mutableListOf(5,0,0,0,0,0,1)
    val line7 = mutableListOf(6,0,0,0,0,0,0)

    val graph = mutableListOf(line1,line2,line3,line4,line5,line6,line7)
    graph.print()
    val minCut = findMinCut(graph)
    println(minCut)

}


private fun buildGraphMatrix(matrixFromFile: Array<IntArray>): MutableList<MutableList<Int>> {
    return MutableList(matrixFromFile.size + 1) { index ->
        if (index == 0) {
            MutableList(matrixFromFile.size + 1) { ind -> ind }
        } else {
            MutableList(matrixFromFile.size + 1) { subIndex ->
                val vertices = matrixFromFile[index - 1]
                if (subIndex == 0) {
                    vertices[0]
                } else {
                    if (vertices.drop(1).contains(subIndex)) {
                        1
                    } else {
                        0
                    }
                }
            }
        }
    }
}

private fun returnMatrixFromFile(): Array<IntArray> {
    val fileName = "kargerMinCut.txt"

    val file = File("/Users/fpr0001/IdeaProjects/Exercises/src/coursera/$fileName")
    return file.useLines { sequence ->
        sequence
                .toList()
                .map {
                    it
                            .split("\t")
                            .mapNotNull { numb ->
                                try {
                                    numb.toInt()
                                } catch (e: NumberFormatException) {
                                    null
                                }
                            }
                            .toIntArray()
                }.toTypedArray()
    }
}

fun MutableList<MutableList<Int>>.print() {
    println("\n")
    forEach { list ->
        list.forEach { value -> print("$value ") }
        println()
    }
}

fun findMinCut(graph: MutableList<MutableList<Int>>): Int {

    if (graph.size == 3) {
        return graph[1][2]
    }

    val randomIndex1 = Random.nextInt(1, graph.size)
    var randomIndex2 = randomIndex1

    while (randomIndex2 == randomIndex1) {
        randomIndex2 = Random.nextInt(1, graph.size)
    }

    val maxIndex = max(randomIndex1, randomIndex2)
    val minIndex = if (maxIndex == randomIndex1) randomIndex2 else randomIndex1

    for (i in 1 until graph.size) {
        if (i == minIndex || i == maxIndex) {
            continue
        }
        graph[i][minIndex] += graph[i][maxIndex]
        if (i > minIndex) {
            graph[minIndex][i] += graph[i][minIndex]
            graph[i][minIndex] = 0
        }
    }
    for (i in minIndex + 1 until graph.size) {
        graph[minIndex][i] += graph[maxIndex][i]
    }

    graph.removeAt(maxIndex)
    graph.forEach { line -> line.removeAt(maxIndex) }
    graph.print()

    return findMinCut(graph)


//    val newGraph = Array(graph.size - 1) { IntArray(graph.size - 1) }
//
//    //TODO merging vertixColumn into vertixRow
//
//    var hasFoundCeasingRow = false
//    for (i in graph.indices) {
//        val rowId = graph[i][0]
//        if (rowId == vertixColumn) {
//            hasFoundCeasingRow = true
//        }
//        var hasFoundCeasingColumn = false
//        for (j in graph[i].indices) {
//            val columnId = graph[0][j]
//            if (columnId == vertixColumn) {
//                hasFoundCeasingColumn = true
//            }
//            if (i == 0) { //if it is header
//                newGraph[0][if (hasFoundCeasingColumn) j - 1 else j] = columnId
//            } else if (j <= i) {
//                newGraph[i][j] = 0
//            } else { //if j > i
//                newGraph[i][j] = graph[i][j]
//
//
//                if (columnId == vertixColumn) {
//                    if (rowId == vertixRow) {
//                        //TODO arestas vão sumir
//                    } else {
//                        newGraph[if (hasFoundCeasingRow) i - 1 else i][if (hasFoundCeasingColumn) j - 1 else j] = graph[i][j] + newGraph[if (hasFoundCeasingRow) i - 1 else i][vertixRowIndex]
//                    }
//                } else {
//                    newGraph[if (hasFoundCeasingRow) i - 1 else i][if (hasFoundCeasingColumn) j - 1 else j] = graph[i][j]
//                }
//            }
//        }
//    }


//    return 0
}