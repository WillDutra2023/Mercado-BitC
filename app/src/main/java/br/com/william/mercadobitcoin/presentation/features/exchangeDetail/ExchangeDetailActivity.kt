package br.com.william.mercadobitcoin.presentation.features.exchangeDetail

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import br.com.william.mercadobitcoin.presentation.features.exchangeDetail.view.ExchangeDetailScreen
import br.com.william.mercadobitcoin.presentation.features.exchangeDetail.viewModel.ExchangeDetailViewModel
import br.com.william.mercadobitcoin.presentation.theme.MercadoBitCoinTheme
import br.com.william.mercadobitcoin.utils.Constant
import kotlinx.coroutines.flow.receiveAsFlow
import org.koin.androidx.viewmodel.ext.android.viewModel

class ExchangeDetailActivity : AppCompatActivity() {

    private val viewModel: ExchangeDetailViewModel by viewModel<ExchangeDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.getStringExtra(Constant.ASSET_ID)?.let { viewModel.getDetailExchange(it) }
        enableEdgeToEdge()
        setContent {
            MercadoBitCoinTheme {
                ExchangeDetailDestination()
            }
        }
    }

    @Composable
    fun ExchangeDetailDestination() {
        ExchangeDetailScreen(
            state = viewModel.state.collectAsState().value,
            effectFlow = viewModel.effects.receiveAsFlow()
        )
    }
}