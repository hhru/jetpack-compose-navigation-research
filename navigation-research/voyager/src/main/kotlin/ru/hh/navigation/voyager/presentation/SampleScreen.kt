package ru.hh.navigation.voyager.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import ru.hh.navigation.common.SampleScreenContent
import ru.hh.navigation.common.randomString
import ru.hh.navigation.voyager.facade.VoyagerDeps
import ru.hh.navigation.voyager.facade.VoyagerSampleFacade
import toothpick.ktp.extension.getInstance

internal class SampleScreen(
    private val title: String,
) : AndroidScreen() {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val bottomSheetNavigator = LocalBottomSheetNavigator.current
        SampleScreenContent(
            openScreen = { navigator.push(SampleScreen(title = randomString())) },
            openDialog = { /* нет решения предоставляевым библиотекой */ },
            openBottomSheet = {
                bottomSheetNavigator.show(SampleScreen(randomString()))
            },
            // этот кейс просто копия кейса ниже, поэтому не будем его рисовать
            startFlowWithoutHeader = null,
            startFlowWithHeader = { navigator.push(FlowNavigationScreen(randomString())) },
            openMultiscreen = { navigator.push(MultiStackScreen(0)) },
            openScreenModel = { },
            openComplexNavigation = { navigator.push(ComplexNavigationScreen()) },
            openFragment = {
                VoyagerSampleFacade().featureScope.getInstance<VoyagerDeps>().openFragment()
            },
            screenTitle = title,
            modifier = Modifier.fillMaxSize()
        )
    }

}
