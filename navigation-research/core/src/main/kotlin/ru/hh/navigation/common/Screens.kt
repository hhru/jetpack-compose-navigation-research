package ru.hh.navigation.common

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlin.random.Random

@Composable
fun SampleScreenContent(
    openScreen: () -> Unit,
    openDialog: () -> Unit,
    openBottomSheet: () -> Unit,
    startFlowWithoutHeader: (() -> Unit)?,
    startFlowWithHeader: () -> Unit,
    openMultiscreen: () -> Unit,
    openScreenModel: () -> Unit,
    openComplexNavigation: () -> Unit,
    modifier: Modifier = Modifier,
    screenTitle: String? = null,
) {
    ScreenWithButtons(
        buttons = listOfNotNull(
            "Open screen" to openScreen,
            "Open dialog" to openDialog,
            "Open bottom sheet" to openBottomSheet,
            startFlowWithoutHeader?.let { "Flow without header" to startFlowWithoutHeader },
            "Flow with header" to startFlowWithHeader,
            "MultiScreen" to openMultiscreen,
            "Complex navigation" to openComplexNavigation,
            "Model sample" to openScreenModel
        ),
        screenTitle = screenTitle,
        modifier = modifier.randomBackground(),
    )
}

@Composable
fun ComplexNavigationSampleContent(
    openStack: () -> Unit,
    closeStack: () -> Unit,
    closePrevScreen: (() -> Unit)?,
    replaceScreen: () -> Unit,
    modifier: Modifier = Modifier,
    screenTitle: String? = null,
) {
    ScreenWithButtons(
        buttons = listOf(
            "Open stack" to openStack,
            "Close stack" to closeStack,
            "Close Prev Screen" to closePrevScreen,
            "Replace Screen" to replaceScreen,
        ),
        screenTitle = screenTitle,
        modifier = modifier.randomBackground()
    )
}

@Composable
fun DialogContent(
    openDialog: () -> Unit,
    openScreen: (closeDialog: Boolean) -> Unit,
    closeDialog: () -> Unit,
    modifier: Modifier = Modifier,
    screenTitle: String? = null,
) {
    ScreenWithButtons(
        buttons = listOf(
            "Open dialog" to openDialog,
            "Close dialog and open screen" to { openScreen(true) },
            "Open screen" to { openScreen(false) },
            "Close dialog" to closeDialog
        ),
        screenTitle = screenTitle,
        modifier = modifier
            .clip(RoundedCornerShape(32.dp))
            .background(Color.White)
            .randomBackground()
            .padding(32.dp),
        isDialog = true
    )
}

@Suppress("MagicNumber")
@Composable
fun ScreenWithButtons(
    buttons: List<Pair<String, (() -> Unit)?>>,
    modifier: Modifier = Modifier,
    screenTitle: String? = null,
    isDialog: Boolean = false
) {
    var counter by rememberSaveable {
        mutableStateOf(0)
    }
    LaunchedEffect(key1 = null) {
        while (isActive) {
            delay(100)
            counter++
        }
    }
    val updatableButton by rememberUpdatedState(newValue = buttons)
    Column(
        modifier = modifier.wrapContentSize(),
        verticalArrangement = Arrangement.Top
    ) {
        val backPressedDispatcher = LocalOnBackPressedDispatcherOwner.current!!.onBackPressedDispatcher
        IconButton(
            onClick = { backPressedDispatcher.onBackPressed() },
            modifier = Modifier.align(Alignment.Start),
        ) {
            Icon(painter = rememberVectorPainter(image = Icons.Outlined.ArrowBack), contentDescription = "Back")
        }
        Column(
            modifier = Modifier
                .weight(1f, fill = !isDialog)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (!screenTitle.isNullOrEmpty()) {
                Text(text = screenTitle)
            }
            Text(text = counter.toString())
            Column(Modifier.width(intrinsicSize = IntrinsicSize.Max)) {
                for (button in updatableButton) {
                    Button(
                        onClick = { button.second?.invoke() },
                        enabled = button.second != null,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = button.first)
                    }
                }
            }
        }
    }
}

fun Modifier.randomBackground() = composed {
    val backgroundColorInt = rememberSaveable { Random.nextInt() }
    val backgroundColor = remember { Color(backgroundColorInt) }
    then(Modifier.background(backgroundColor))
}

@Preview
@Composable
private fun NavigationScreenContentPreview() {
    MaterialTheme {
        SampleScreenContent(
            openScreen = {},
            openDialog = {},
            openBottomSheet = {},
            startFlowWithoutHeader = {},
            startFlowWithHeader = {},
            openMultiscreen = {},
            openScreenModel = {},
            openComplexNavigation = {},
            screenTitle = "Screen 0",
            modifier = Modifier.fillMaxSize()
        )
    }
}
