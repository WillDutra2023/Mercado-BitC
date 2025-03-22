package br.com.william.mercadobitcoin.presentation.features.exchange

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.core.app.ActivityOptionsCompat
import br.com.william.mercadobitcoin.presentation.features.exchange.view.ExchangeScreen
import br.com.william.mercadobitcoin.presentation.features.exchange.viewModel.ExchangeViewModel
import br.com.william.mercadobitcoin.presentation.features.exchangeDetail.ExchangeDetailActivity
import br.com.william.mercadobitcoin.presentation.theme.MercadoBitCoinTheme
import br.com.william.mercadobitcoin.utils.Constant
import kotlinx.coroutines.flow.receiveAsFlow
import org.koin.androidx.viewmodel.ext.android.viewModel

class ExchangeActivity : AppCompatActivity() {
    private val viewModel: ExchangeViewModel by viewModel<ExchangeViewModel>()
    private val myActivityResultContract =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                viewModel.getListExchange()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MercadoBitCoinTheme {
                ExchangeDestination()
            }
        }
    }

    @Composable
    fun ExchangeDestination() {
        ExchangeScreen(
            state = viewModel.state.collectAsState().value,
            effectFlow = viewModel.effects.receiveAsFlow(),
            onNavigationRequested = { idIcon: String, assetId: String ->
                myActivityResultContract.launch(
                    Intent(
                        this@ExchangeActivity, ExchangeDetailActivity::class.java
                    ).apply {
                        putExtra(
                            Constant.ID_ICON, idIcon
                        )
                        putExtra(Constant.ASSET_ID, assetId)
                    }, ActivityOptionsCompat.makeSceneTransitionAnimation(this as Activity)
                )
            })
    }

}