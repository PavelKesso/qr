package by.radiance.qr.builder

import by.radiance.qr.builder.pattern.Pattern.*
import by.radiance.qr.builder.positon.Position
import by.radiance.qr.builder.version.Version

class QrBuilder {
    fun build(version: Version): QrPattern {
        val finders = finders(version)
        val separators = separators(version)
        val alignments = alignments(version, finders, separators)
        val timings = timings(version)

        return QrPattern(
            version,
            finders,
            alignments,
            timings,
            separators,
        )
    }

    private fun finders(version: Version): List<Finder> {
        return listOf(
            Finder(Position(3, 3)),
            Finder(Position(3, version.size - 3)),
            Finder(Position(version.size - 3, 3 )),
        )
    }

    private fun separators(version: Version): List<Separator> {
        return listOf(
            Separator.Vertical(Position(0, 7), Position(7, 7)),
            Separator.Horizontal(Position(7, 0), Position(7, 6 )),
            Separator.Vertical(Position(0, version.size - 7), Position(7, version.size - 7)),
            Separator.Horizontal(Position(7, version.size - 6), Position(7, version.size)),
            Separator.Horizontal(Position(version.size - 7, 0), Position(version.size - 7, 7)),
            Separator.Vertical(Position(version.size - 6, 7), Position(version.size, 7)),
        )
    }

    private fun alignments(
        version: Version,
        finders: List<Finder>,
        separators: List<Separator>
    ): List<Alignment> {
        val alignments = version.alignments.map { x ->
            version.alignments.map { y ->
                Alignment(Position(x, y))
            }
        }.flatten()
        val existedCells = finders.map { it.toCells() }.flatten() + separators.map { it.toCells() }.flatten()

        return alignments.filter { alignment ->
            alignment.toCells().none { cell -> existedCells.contains(cell) }
        }
    }

    private fun timings(version: Version): List<Timing> {
        return listOf(
            Timing(Position(8, 6), Position(version.size - 8, 6)),
            Timing(Position(6, 8), Position(6, version.size - 8)),
        )
    }

    private fun Finder.toCells(): List<Position> {
        val answer = mutableListOf<Position>()
        for (x in -3..3) {
            for (y in -3..3) {
                answer.add(Position(center.x + x, center.y + y))
            }
        }
        return answer
    }

    private fun Separator.toCells(): List<Position> {
        val answer = mutableListOf<Position>()
        when (this) {
            is Separator.Vertical -> {
                for (x in start.x..end.x) {
                    answer.add(Position(x, start.y))
                }
            }
            is Separator.Horizontal -> {
                for (y in start.y..end.y) {
                    answer.add(Position(start.x, y))
                }
            }
        }
        return answer
    }

    private fun Alignment.toCells(): List<Position> {
        val answer = mutableListOf<Position>()
        for (x in -2..2) {
            for (y in -2..2) {
                answer.add(Position(center.x + x, center.y + y))
            }
        }
        return answer
    }
}