package coursera

import java.io.File

/**
 * File must be in /Users/fpr0001/IdeaProjects/Exercises/src/coursera/
 */
fun getIntArrayFromFile(fileName: String): IntArray {
    val file = File("/Users/fpr0001/IdeaProjects/Exercises/src/coursera/$fileName")
    return file.useLines { sequence ->
        sequence.toList().map { it.toInt() }.toIntArray()
    }
}