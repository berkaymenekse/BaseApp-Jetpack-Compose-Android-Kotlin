package com.merttoptas.composebase.features.screen.settings

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.merttoptas.composebase.R
import androidx.hilt.navigation.compose.hiltViewModel
import com.merttoptas.composebase.BuildConfig
import com.merttoptas.composebase.domain.viewstate.settings.SettingsViewState
import com.merttoptas.composebase.features.component.RickAndMortyScaffold
import com.merttoptas.composebase.features.component.RickAndMortyText
import com.merttoptas.composebase.features.component.RickAndMortyTopBar

/**
 * Created by merttoptas on 22.03.2022
 */

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
) {
    val scaffoldState = rememberScaffoldState()
    val viewState by viewModel.uiState.collectAsState()

    RickAndMortyScaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            RickAndMortyTopBar(
                text = stringResource(id = R.string.settings_screen_title),
                elevation = 10.dp,
            )
        },
        content = { Content(viewModel, viewState) },
        backgroundColor = MaterialTheme.colors.background
    )

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Content(viewModel: SettingsViewModel, viewState: SettingsViewState) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp),
            shape = RoundedCornerShape(8.dp),
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    RickAndMortyText(
                        text = stringResource(id = R.string.settings_screen_title),
                        style = MaterialTheme.typography.body2
                    )

                    Switch(
                        checked = viewState.isDark,
                        onCheckedChange = { viewModel.onTriggerEvent(SettingsViewEvent.OnChangeTheme) })
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    RickAndMortyText(
                        text = stringResource(id = R.string.settings_screen_app_version_title),
                        style = MaterialTheme.typography.body2
                    )

                    RickAndMortyText(
                        text = BuildConfig.VERSION_NAME,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }
}
