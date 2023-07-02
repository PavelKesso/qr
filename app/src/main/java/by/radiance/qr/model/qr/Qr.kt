package by.radiance.qr.model.qr

import by.radiance.qr.model.position.Position
import by.radiance.qr.model.state.CellColor

data class Qr(
    val size: Int,
    val preset: List<List<Cell>>,
) {
    data class Cell(
        val position: Position,
        val states: List<CellColor>,
        val enabled: Boolean,
        val initialState: CellColor,
    )
}

fun defaultCell(x: Int, y: Int): Qr.Cell = Qr.Cell(
    position = Position(x, y),
    states = listOf(CellColor.White, CellColor.Black),
    enabled = true,
    initialState = CellColor.White,
)