package br.com.william.mercadobitcoin.presentation.features.exchange.view

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.william.mercadobitcoin.R
import br.com.william.mercadobitcoin.domain.model.ExchangeItem
import br.com.william.mercadobitcoin.presentation.components.EmptyView
import br.com.william.mercadobitcoin.presentation.components.LoadingBar
import br.com.william.mercadobitcoin.presentation.components.TopbarAppComponents
import br.com.william.mercadobitcoin.presentation.contracts.BaseContract
import br.com.william.mercadobitcoin.presentation.contracts.ExchangeContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExchangeScreen(
    state: ExchangeContract.State,
    effectFlow: Flow<BaseContract.Effect>?,
    onNavigationRequested: (idIcon: String, assetId: String) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    val messageLoading = stringResource(R.string.loading_data)

    LaunchedEffect(effectFlow) {
        effectFlow?.onEach { effect ->
            if (effect is BaseContract.Effect.DataWasLoaded)
                snackBarHostState.showSnackbar(
                    message = messageLoading,
                    duration = SnackbarDuration.Short
                )
        }?.collect { value ->
            if (value is BaseContract.Effect.Error) {
                // Handle other emitted values if needed
                Toast.makeText(context, value.errorMessage, Toast.LENGTH_LONG).show()
            }

        }
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopbarAppComponents(scrollBehavior)
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues = paddingValues)
        ) {
            UserView(
                state,
                onNavigationRequested = onNavigationRequested
            )
        }
    }
}

@Composable
fun UserView(
    state: ExchangeContract.State,
    onNavigationRequested: (idIcon: String, assetId: String) -> Unit
) {
    Box {
        if (state.isLoading)
            return LoadingBar()

        val exchange = state.exchange
        if (exchange.isEmpty()) {
            EmptyView(message = stringResource(R.string.loading_data))
        } else {
            ExchangeList(exchangeList = exchange) { idIcon, imageId ->
                onNavigationRequested(idIcon, imageId)
            }

        }
    }

}

@Composable
fun ExchangeList(
    exchangeList: List<ExchangeItem>,
    onItemClicked: (idIcon: String, assetId: String) -> Unit = { _: String, _: String -> },
) {
    LazyColumn(
        state = rememberLazyListState(),
        contentPadding = PaddingValues(10.dp),
        modifier = Modifier.fillMaxSize(),
        content = {
            items(
                count = exchangeList.size,
                itemContent = { item ->
                    ListItem(
                        headlineContent = { Text(exchangeList[item].name.toString()) },
                        overlineContent = { Text(exchangeList[item].assetId.toString()) },
                        trailingContent = { Text(exchangeList[item].priceUsd.toString()) },
                        modifier = Modifier
                            .animateItem(
                                // Optionally add custom animation specs
                            )
                            .fillParentMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 0.dp)
                            .clickable {
                                onItemClicked(
                                    exchangeList[item].idIcon.toString(),
                                    exchangeList[item].assetId.toString()
                                )
                            },

                        )
                }
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ExchangeScreen(
        ExchangeContract.State(),
        effectFlow = null,
        onNavigationRequested = { _: String, _: String -> }
    )
}