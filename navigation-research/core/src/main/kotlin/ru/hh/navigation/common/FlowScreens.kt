package ru.hh.navigation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.hh.navigation.common.progress.ProgressBar

const val STEPS_COUNT = 3

@Composable
fun FlowScreenContainer(
    step: Int,
    stepsCount: Int,
    modifier: Modifier = Modifier,
    innerContent: @Composable () -> Unit,
) {
    Column(modifier) {
        ProgressBar(step = step, stepsCount = stepsCount)
        Box(Modifier.fillMaxSize()) {
            innerContent()
        }
    }
}

@Composable
fun StepContent(
    text: String,
    isOnNextEnabled: Boolean,
    onNextClick: () -> Unit,
    onFinishFlowClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = text)
        Column(
            Modifier
                .width(IntrinsicSize.Max)
        ) {
            Button(
                onClick = onNextClick,
                enabled = isOnNextEnabled,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Next step")
            }
            Button(
                onClick = onFinishFlowClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Finish flow")
            }
        }
    }
}

@Preview
@Composable
private fun PreviewFlow() {
    MaterialTheme {
        FlowScreenContainer(
            step = 2,
            stepsCount = 3
        ) {
            StepContent(
                text = "Screen",
                isOnNextEnabled = true,
                onFinishFlowClick = {},
                onNextClick = {}
            )
        }
    }
}

@Preview
@Composable
private fun PreviewFlow2() {
    MaterialTheme {
        FlowScreenContainer(
            step = 3,
            stepsCount = 3
        ) {
            StepContent(
                text = "Screen",
                isOnNextEnabled = false,
                onFinishFlowClick = {},
                onNextClick = {}
            )
        }
    }
}
