package by.radiance.qr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.radiance.qr.builder.QrBuilder
import by.radiance.qr.builder.version.Version
import by.radiance.qr.model.position.Position
import by.radiance.qr.model.qr.Qr
import by.radiance.qr.model.qr.blackCell
import by.radiance.qr.model.qr.defaultCell
import by.radiance.qr.model.qr.whiteCell
import by.radiance.qr.ui.cell.Cell
import by.radiance.qr.ui.qr.Qr
import by.radiance.qr.ui.theme.QrTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QrTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var versionNumber by remember { mutableStateOf(10) }
                    val version = Version(versionNumber)
                    val width = (LocalConfiguration.current.screenWidthDp * 0.95).toInt()
                    val size = version.size

                    val cellSize = width / size
                    val qr = QrBuilder().build(version).toCells()

                    val preset = Qr(
                        size = version.size,
                        preset = (0..version.size).map { x ->
                            (0..version.size).map { y ->
                                val cell = qr.firstOrNull { cell ->
                                    cell.first.x == x && cell.first.y == y
                                }

                                if (cell == null) {
                                    defaultCell(x, y)
                                } else {
                                    if (cell.second) {
                                        blackCell(x, y)
                                    } else {
                                        whiteCell(x, y)
                                    }
                                }
                            }
                        }
                    )

                    Qr(cellSize = cellSize.dp, preset = preset, onChanged = {})
                }
            }
        }
    }
}