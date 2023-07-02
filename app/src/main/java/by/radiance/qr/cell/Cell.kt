package by.radiance.qr.cell

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.radiance.qr.cell.model.CellState
import by.radiance.qr.theme.QrTheme
import by.radiance.qr.utils.getNext

@Composable
fun Cell(
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.small,
    enabled: Boolean,
    states: List<CellState>,
    startState: CellState = states.first(),
    onStateChanged: (CellState) -> Unit
) {
    var state by remember { mutableStateOf(startState)}

    Box(
        modifier = modifier
            .background(state.color, shape)
            .clickable(enabled) {
                state = states.getNext(state)
                onStateChanged.invoke(state)
            },
    )
}

@Preview
@Composable
fun CellPreview() {
    QrTheme {
        Row {
            Cell(
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp)
                ,
                shape = MaterialTheme.shapes.small.copy(all = CornerSize(3.dp)),
                enabled = true,
                states = listOf(CellState(1, Color.Black), CellState(2, Color.White), CellState(3, Color.Yellow)),
                onStateChanged = {}
            )
            Cell(
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp)
                ,
                shape = MaterialTheme.shapes.small.copy(all = CornerSize(3.dp)),
                enabled = false,
                states = emptyList(),
                startState = CellState(1, Color.Black),
                onStateChanged = {}
            )
        }
    }
}