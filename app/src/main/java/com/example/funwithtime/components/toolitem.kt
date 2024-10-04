package com.example.funwithtime.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.funwithtime.lastclicked
import kotlinx.coroutines.*
import kotlin.math.floor

suspend fun getTimestampInSeconds(timeMax: Long, onUpdate: (String) -> Unit) {
    while (true) {
        val time = floor(System.currentTimeMillis() / 1000.0).toLong()
        val timeLeft = timeMax - time
        val d = floor(timeLeft / (3600 * 24).toDouble()).toLong()
        val h = floor((timeLeft % (3600 * 24)) / 3600.0).toLong()
        val m = floor((timeLeft % 3600) / 60.0).toLong()
        val s = floor((timeLeft % 60).toDouble()).toLong()

        val hS : String = if (h < 10) { "0$h" } else h.toString()
        val dS: String = if (d < 10) { "0$d" } else d.toString()
        val mS: String = if (m < 10) { "0$m" } else m.toString()
        val sS: String = if (s < 10) {"0$s"} else s.toString()

        onUpdate("$dS:$hS:$mS:$sS")

        delay(1000)
    }
}

@Preview
@Composable
fun CarouselCard(
    label : Int = 1
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(10.dp),
        color = colorScheme.primary,
        ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(4.dp))
        }

    }
}


@Composable
fun TimerCard(
    label : String,
    timeMax: Long
) {
    var timeToDisplay by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        launch {
            getTimestampInSeconds(timeMax) { newDisplayText ->
                timeToDisplay = newDisplayText
            }
        }
    }

    Surface(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .height(240.dp)
            .padding(10.dp),
        shadowElevation = 10.dp
    ) {

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
               Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = label,
                    fontSize = 24.sp,
                    style = typography.titleLarge,
                    fontWeight = SemiBold
                )

                Spacer(modifier = Modifier.height(2.dp))

                Spacer(modifier = Modifier.height(4.dp))

                Surface(
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier
                        .wrapContentSize(),
                    color = colorScheme.inverseOnSurface,

                    ) {
                    Text(
                        text = timeToDisplay,
                        fontSize = 40.sp,
                        style = typography.titleLarge,
                        fontWeight = SemiBold,
                        modifier = Modifier.padding(15.dp),
                    )
                }
            }
        }
}

@Composable
fun ToolItem(
    title: String = "Title",
    description: String = "Description",
    id: Int,
    onClick: () -> Unit,

    ) {
    Card(
        colors = CardDefaults.cardColors(containerColor = colorScheme.surfaceColorAtElevation(2.dp)),
        shape = shapes.extraLarge,
        modifier = Modifier
            .padding(horizontal = 18.dp, vertical = 5.dp)
            .fillMaxWidth(),

    ) {

        Box(
            modifier = Modifier
                .clip(shapes.extraLarge)
                .clickable(enabled = true, onClick = onClick),
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 22.dp, vertical = 18.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier
                            .padding(end = 10.dp, bottom = 6.dp)
                            .weight(1f, true)
                    ) {
                        Text(
                            text = title,
                            style = typography.headlineSmall,
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .clip(shapes.extraSmall),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = description,
                        style = typography.bodyMedium,
                    )
                }
            }
        }
    }
}
