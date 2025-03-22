package br.com.william.mercadobitcoin.presentation.features.exchangeDetail.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.william.mercadobitcoin.core.UiState
import br.com.william.mercadobitcoin.domain.usecase.GetExchangeDetailUseCase
import br.com.william.mercadobitcoin.domain.usecase.GetExchangeUsecase
import br.com.william.mercadobitcoin.presentation.contracts.BaseContract
import br.com.william.mercadobitcoin.presentation.contracts.ExchangeContract
import br.com.william.mercadobitcoin.utils.ErrorsMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExchangeDetailViewModel(
    private val exchangeDetailUseCase: GetExchangeDetailUseCase
) : ViewModel() {

    var effects = Channel<BaseContract.Effect>(Channel.UNLIMITED)

    private val _state = MutableStateFlow(
        ExchangeContract.State(
            exchange = listOf(),
            isLoading = true
        )
    )
    val state: StateFlow<ExchangeContract.State> = _state

    private fun updateState(newState: ExchangeContract.State) {
        _state.value = newState
    }

    fun getDetailExchange(assetId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            exchangeDetailUseCase.execute(assetId).collect {
                when (it) {
                    is UiState.Success -> {
                        val newState = _state.value.copy(exchange = it.data!!, isLoading = false)
                        updateState(newState)
                        effects.send(BaseContract.Effect.DataWasLoaded)
                    }

                    is UiState.Error -> {
                        val newState = state.value.copy(isLoading = false)
                        updateState(newState)
                        effects.send(
                            BaseContract.Effect.Error(
                                it.message ?: ErrorsMessage.gotApiCallError
                            )
                        )
                    }

                    is UiState.Loading -> {
                        if (!state.value.isLoading) {
                            val newState = state.value.copy(isLoading = true)
                            updateState(newState)
                        }
                    }
                }
            }
        }
    }
}