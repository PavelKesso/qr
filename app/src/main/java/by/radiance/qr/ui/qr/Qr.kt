package by.radiance.qr.ui.qr

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import by.radiance.qr.model.qr.Qr
import by.radiance.qr.model.qr.defaultCell
import by.radiance.qr.ui.cell.Cell
import by.radiance.qr.ui.theme.QrTheme

@Composable
fun Qr(
    modifier: Modifier = Modifier,
    cellSize: Dp,
    preset: Qr,
    onChanged: (Qr) -> Unit
) {
    Row {
        preset.preset.forEach { row ->
            Column {
                row.forEach { cell ->
                    Cell(
                        modifier = Modifier
                            .width(cellSize)
                            .height(cellSize),
                        cell = cell,
                        onStateChanged = {}
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun QrPreview() {
    QrTheme {
        Qr(
            cellSize = 25.dp,
            preset = Qr(
                size = 5,
                preset = (1..15).map {x ->
                    (1..15).map { y ->
                        defaultCell(x, y)
                    }
                }
            ),
            onChanged = {}
        )
    }
}