package ru.hh.navigation.appyx.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.bumble.appyx.navmodel.backstack.BackStack
import com.bumble.appyx.navmodel.backstack.operation.push
import ru.hh.navigation.appyx.facade.AppyxDeps
import ru.hh.navigation.appyx.facade.AppyxSampleFacade
import ru.hh.navigation.common.SampleScreenContent
import ru.hh.navigation.common.randomString
import toothpick.ktp.extension.getInstance

internal class SampleScreen(
    buildContext: BuildContext,
    private val title: String,
    private val backStack: BackStack<NavTarget>,
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val showNotSupported = LocalShowNotSupported.current
        SampleScreenContent(
            openScreen = { backStack.push(NavTarget.SampleScreen(randomString())) },
            openDialog = { showNotSupported() },
            openBottomSheet = { showNotSupported() },
            // этот кейс просто копия кейса ниже, поэтому не будем его рисовать
            startFlowWithoutHeader = null,
            startFlowWithHeader = { backStack.push(NavTarget.Flow(randomString())) },
            openMultiscreen = { backStack.push(NavTarget.MultiStack) },
            openScreenModel = { backStack.push(NavTarget.Stack()) },
            openComplexNavigation = { backStack.push(NavTarget.ComplexNavigation(randomString())) },
            openFragment = {
                AppyxSampleFacade().featureScope.getInstance<AppyxDeps>().openFragment()
            },
            screenTitle = title,
            modifier = Modifier.fillMaxSize()
        )
    }

}
