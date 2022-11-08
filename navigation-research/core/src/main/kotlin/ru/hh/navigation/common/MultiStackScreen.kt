package ru.hh.navigation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview

val TABS = listOf(
    Icons.Outlined.Home to "first",
    Icons.Outlined.List to "second",
    Icons.Outlined.Star to "third"
)

@Composable
fun MultiStackScreenContent(
    selectedPos: Int,
    onTabClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    tabs: List<Pair<ImageVector, String>> = TABS,
    innerContent: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        bottomBar = {
            BottomNavigation {
                tabs.forEachIndexed { index, item ->
                    BottomNavigationItem(
                        selected = selectedPos == index,
                        onClick = { onTabClick(index) },
                        icon = {
                            Column(horizontalAlignment = CenterHorizontally) {
                                Icon(
                                    painter = rememberVectorPainter(item.first),
                                    contentDescription = item.second
                                )
                                Text(text = item.second)
                            }
                        },
                    )
                }
            }
        },
        modifier = modifier
    ) { innerContent(it) }
}

@Preview
@Composable
private fun PreviewMultiStack() {
    MaterialTheme {
        MultiStackScreenContent(
            tabs = TABS,
            selectedPos = 1,
            onTabClick = {}
        ) {
            Text(text = "Hello!")
        }
    }
}
