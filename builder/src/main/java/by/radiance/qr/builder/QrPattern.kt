package by.radiance.qr.builder

import by.radiance.qr.builder.pattern.Pattern
import by.radiance.qr.builder.positon.Position
import by.radiance.qr.builder.version.Version
import kotlin.math.abs

data class QrPattern(
    val version: Version,
    val finders: List<Pattern.Finder>,
    val alignments: List<Pattern.Alignment>,
    val timings: List<Pattern.Timing>,
    val separators: List<Pattern.Separator>,
) {
    fun toCells(): List<Pair<Position, Boolean>> {
        val finderCells = finders.map { finder ->
            (-3..3).map { x ->
                (-3..3).map { y ->
                    val cx = finder.center.x + x
                    val cy = finder.center.y + y
                    val isWhite = (abs(x) == 2 || abs(y) == 2) && (abs(x) != 3 && abs(y) != 3)
                    Position(cx, cy) to !isWhite
                }
            }.flatten()
        }.flatten()

        val alignmentCells = alignments.map { alignment ->
            (-2..2).map { x ->
                (-2..2).map { y ->
                    val cx = alignment.center.x + x
                    val cy = alignment.center.y + y
                    val isWhite = (abs(x) == 1 || abs(y) == 1) && (abs(x) != 2 && abs(y) != 2)
                    Position(cx, cy) to !isWhite
                }
            }.flatten()
        }.flatten()

        val timingCells = timings.map { timing ->
            if (timing.start.x == timing.end.x) {
                (timing.start.y..timing.end.y).map { y ->
                    Position(timing.start.x, y) to (y % 2 == 0)
                }
            } else {
                (timing.start.x..timing.end.x).map { x ->
                    Position(x, timing.start.y) to (x % 2 == 0)
                }
            }
        }.flatten()

        val separatorCells = separators.map { separator ->
            when(separator) {
                is Pattern.Separator.Horizontal -> {
                    (separator.start.y..separator.end.y).map { y ->
                        Position(separator.start.x, y) to false
                    }
                }
                is Pattern.Separator.Vertical ->
                    (separator.start.x..separator.end.x).map { x ->
                        Position(x, separator.start.y) to false
                    }
            }
        }.flatten()

        return finderCells + alignmentCells + timingCells + separatorCells
    }
}