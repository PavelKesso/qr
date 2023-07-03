package by.radiance.qr.builder.pattern

import by.radiance.qr.builder.positon.Position

sealed class Pattern {
    data class Finder(val center: Position): Pattern()
    data class Alignment(val center: Position): Pattern()
    data class Timing(val start: Position, val end: Position): Pattern()
    sealed class Separator: Pattern() {
        data class Vertical(val start: Position, val end: Position): Separator()
        data class Horizontal(val start: Position, val end: Position): Separator()
    }
}
