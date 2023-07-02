package by.radiance.qr.ui.cell.model

import androidx.compose.ui.graphics.Color
import by.radiance.qr.model.state.CellColor

data class CellState(
    val cellColor: CellColor,
    val color: Color,
)
