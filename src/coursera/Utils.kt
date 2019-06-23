package coursera

import java.io.File

/**
 * File must be in /Users/fpr0001/IdeaProjects/coursera/src/
 */
fun getIntArrayFromFile(fileName: String): IntArray {
    val file = File("/Users/fpr0001/IdeaProjects/coursera/src/$fileName")
    return file.useLines { sequence ->
        sequence.toList().map { it.toInt() }.toIntArray()
    }
}