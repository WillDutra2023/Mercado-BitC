package br.com.william.mercadobitcoin.presentation.features.exchangeDetail.view

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
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
fun ExchangeDetailScreen(
    state: ExchangeContract.State,
    effectFlow: Flow<BaseContract.Effect>?
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    val messageLoading = stringResource(R.string.loading_data)

    /**
     * using the rememberNavController()
     * to get the instance of the navController
     */
    val navController = rememberNavController()

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
            DetailView(
                state
            )
        }
    }
}

@Composable
fun DetailView(
    state: ExchangeContract.State
) {
    Box {
        if (state.isLoading)
            return LoadingBar()

        val exchange = state.exchange
        if (exchange.isEmpty()) {
            EmptyView(message = stringResource(R.string.loading_data))
        } else {
            ExchangeDetail(exchangeDetail = exchange)
        }
    }

}

@Composable
fun ExchangeDetail(
    exchangeDetail: List<ExchangeItem>,
) {
    val exchangeDetail = exchangeDetail[0]
    Column {
        Row(
            modifier = Modifier.padding(20.dp).fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(
                        BorderStroke(2.dp, Color.Gray),
                        CircleShape
                    )
                    .clip(CircleShape)

            )
            Column(
                modifier = Modifier.padding(start = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "${exchangeDetail.name}"
                )
                Text(
                    text = "${exchangeDetail.priceUsd}",
                    modifier = Modifier.padding(
                        top = 10.dp
                    )
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ExchangeDetailScreen(
        ExchangeContract.State(),
        effectFlow = null,
    )
}