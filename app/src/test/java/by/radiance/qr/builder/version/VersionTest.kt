package by.radiance.qr.builder.version

import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class VersionTest {

    private val sizes = mapOf(
        1 to 21,
        2 to 25,
        3 to 29,
        4 to 33,
        5 to 37,
        6 to 41,
        7 to 45,
        8 to 49,
        9 to 53,
        10 to 57,
        11 to 61,
        12 to 65,
        13 to 69,
        14 to 73,
        15 to 77,
        16 to 81,
        17 to 85,
        18 to 89,
        19 to 93,
        20 to 97,
        21 to 101,
        22 to 105,
        23 to 109,
        24 to 113,
        25 to 117,
        26 to 121,
        27 to 125,
        28 to 129,
        29 to 133,
        30 to 137,
        31 to 141,
        32 to 145,
        33 to 149,
        34 to 153,
        35 to 157,
        36 to 161,
        37 to 165,
        38 to 169,
        39 to 173,
        40 to 177,
    )

    private val alignments = mapOf(
        1 to emptyList(),
        2 to listOf(6, 18),
        3 to listOf(6, 22),
        4 to listOf(6, 26),
        5 to listOf(6, 30),
        6 to listOf(6, 34),
        7 to listOf(6, 22, 38),
        8 to listOf(6, 24, 42),
        9 to listOf(6, 26, 46),
        10 to listOf(6, 28, 50),
        11 to listOf(6, 30, 54),
        12 to listOf(6, 32, 58),
        13 to listOf(6, 34, 62),
        14 to listOf(6, 26, 46, 66),
        15 to listOf(6, 26, 48, 70),
        16 to listOf(6, 26, 50, 74),
        17 to listOf(6, 30, 54, 78),
        18 to listOf(6, 30, 56, 82),
        19 to listOf(6, 30, 58, 86),
        20 to listOf(6, 34, 62, 90),
        21 to listOf(6, 28, 50, 72, 94),
        22 to listOf(6, 26, 50, 74, 98),
        23 to listOf(6, 30, 54, 78, 102),
        24 to listOf(6, 28, 54, 80, 106),
        25 to listOf(6, 32, 58, 84, 110),
        26 to listOf(6, 30, 58, 86, 114),
        27 to listOf(6, 34, 62, 90, 118),
        28 to listOf(6, 26, 50, 74, 98, 122),
        29 to listOf(6, 30, 54, 78, 102, 126),
        30 to listOf(6, 26, 52, 78, 104, 130),
        31 to listOf(6, 30, 56, 82, 108, 134),
        32 to listOf(6, 34, 60, 86, 112, 138),
        33 to listOf(6, 30, 58, 86, 114, 142),
        34 to listOf(6, 34, 62, 90, 118, 146),
        35 to listOf(6, 30, 54, 78, 102, 126, 150),
        36 to listOf(6, 24, 50, 76, 102, 128, 154),
        37 to listOf(6, 28, 54, 80, 106, 132, 158),
        38 to listOf(6, 32, 58, 84, 110, 136, 162),
        39 to listOf(6, 26, 54, 82, 110, 138, 166),
        40 to listOf(6, 30, 58, 86, 114, 142, 170),
    )

    @Test
    fun getSize() {
        for (version in 1..40) {
            assertEquals(Version(version).size, sizes[version])
        }
    }

    @Test
    fun getAlignments() {
        for (version in 16..40) {
            assertEquals(Version(version).alignments, alignments[version])
        }
    }
}