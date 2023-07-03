package by.radiance.qr.builder.version

import java.lang.IllegalStateException
import kotlin.io.path.createTempDirectory

data class Version(
    val versionNumber: Int
) {
    init {
        if (versionNumber !in (MAX_VERSION downTo 1))
            throw IllegalStateException("version should from 1 to $MAX_VERSION")
    }

    val size = 20 + ((versionNumber - 1) * 4)

    val alignments: List<Int>
        get() {
            if (versionNumber == 1) return emptyList()
            if (versionNumber == 16) return listOf(6, 26, 50, 74)
            if (versionNumber == 19) return listOf(6, 30, 58, 86)
            if (versionNumber == 22) return listOf(6, 26, 50, 74, 98)
            if (versionNumber == 24) return listOf(6, 28, 54, 80, 106)
            if (versionNumber == 26) return listOf(6, 30, 58, 86, 114)
            if (versionNumber == 28) return listOf(6, 26, 50, 74, 98, 122)
            if (versionNumber == 30) return listOf(6, 26, 52, 78, 104, 130)
            if (versionNumber == 32) return listOf(6, 34, 60, 86, 112, 138)
            if (versionNumber == 33) return listOf(6, 30, 58, 86, 114, 142)
            if (versionNumber == 36) return listOf(6, 24, 50, 76, 102, 128, 154)
            if (versionNumber == 37) return listOf(6, 28, 54, 80, 106, 132, 158)
            if (versionNumber == 39) return listOf(6, 26, 54, 82, 110, 138, 166)
            if (versionNumber == 40) return listOf(6, 30, 58, 86, 114, 142, 170)


            val start = 6
            val end = size - 7

            val additionAlignmentCount = ((end - start) / 28.5).toInt()
            val answer = mutableListOf(start)

            if (additionAlignmentCount > 0)
                for (i in 0 until additionAlignmentCount) {
                    val diff = end - answer.last()
                    var step = diff / ((additionAlignmentCount + 1) - i)
                    if (step % 2 != 0) {
                        step--
                    }

                    answer.add(answer.last() + step)
                }

            answer.add(end)
            return answer
        }

    companion object {
        const val MAX_VERSION = 40
    }
}