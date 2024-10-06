package com.example.funwithtime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.funwithtime.components.ToolItem
import com.example.funwithtime.ui.theme.BrainsToolboxTheme
import kotlinx.coroutines.launch

class Tools : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BrainsToolboxTheme {
                    ToolsScreen()
            }
        }
    }
}
data class Item(
    val day: String,
    @DrawableRes val imageResId: Int,
    val text: String,
)

var lastclicked  = 0

val items =
    listOf(
        Item("01", R.drawable.walle, ""),
        Item("02", R.drawable.walle, ""),
        Item("03", R.drawable.walle, ""),
        Item("04", R.drawable.walle, ""),
        Item("05", R.drawable.walle, ""),
        Item("06", R.drawable.walle, ""),
        Item("07", R.drawable.walle, ""),
        Item("08", R.drawable.walle, ""),
        Item("09", R.drawable.walle, ""),
        Item("10", R.drawable.walle, ""),
        Item("11", R.drawable.walle, ""),
        Item("12", R.drawable.walle, ""),
        Item("13", R.drawable.walle, ""),
        Item("14", R.drawable.walle, ""),
        Item("15", R.drawable.walle, ""),
        Item("16", R.drawable.walle, ""),
        Item("17", R.drawable.walle, ""),
        Item("18", R.drawable.walle, ""),
        Item("19", R.drawable.walle, ""),
        Item("20", R.drawable.walle, ""),
        Item("21", R.drawable.walle, ""),
        Item("22", R.drawable.walle, ""),
        Item("23", R.drawable.walle, ""),
        Item("24", R.drawable.walle, ""),
    )


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HorizontalMultiBrowseCarouselSample() {
    HorizontalMultiBrowseCarousel(
        state = rememberCarouselState { 5 },
        modifier = Modifier
            .width(412.dp)
            .height(250.dp),
        preferredItemWidth = 186.dp,
        itemSpacing = 8.dp,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) { i  ->
        val item = items[i]
        Box (Modifier.maskClip(MaterialTheme.shapes.extraLarge)){
            Image(
                modifier = Modifier
                    .height(200.dp),
                painter = painterResource(id = item.imageResId),
                contentDescription = item.day.toString(),
                contentScale = ContentScale.Crop
            )
            Surface(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(0.dp),

                color = MaterialTheme.colorScheme.inverseOnSurface,

            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = "${item.day}.12.2024",
                    modifier = Modifier
                        .padding(15.dp),
                )
            }
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ToolsScreen() {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    Scaffold { padding ->
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
        ) {
            Text(text = "")
            Text(text = "")

            HorizontalMultiBrowseCarouselSample()

            var j = 1
            while (j <= items.size) {
                ToolItem("$j", "Tag Nr.$j", "???") { showBottomSheet = true; lastclicked = j.toInt()-2}
                j++
            }
        }
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState
            ) {
                Image(
                    modifier = Modifier,
                    painter = painterResource(id = items[lastclicked].imageResId),
                    contentDescription = items[lastclicked].day,
                    contentScale = ContentScale.Fit
                )
                Text(
                    textAlign = TextAlign.Center,
                    text = "${items[lastclicked].day}.12.2024",
                    modifier = Modifier
                        .padding(15.dp),
                )

                // Sheet content
                Button(onClick = {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            showBottomSheet = false
                        }
                    }
                }) {
                    Text("Hide bottom sheet")
                }
            }
        }
    }
}
