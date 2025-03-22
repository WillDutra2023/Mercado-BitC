package br.com.william.mercadobitcoin.presentation.features.exchange.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.william.mercadobitcoin.core.UiState
import br.com.william.mercadobitcoin.domain.usecase.GetExchangeUsecase
import br.com.william.mercadobitcoin.presentation.contracts.BaseContract
import br.com.william.mercadobitcoin.presentation.contracts.ExchangeContract
import br.com.william.mercadobitcoin.utils.ErrorsMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExchangeViewModel(
    private val exchangeUsecase: GetExchangeUsecase
) : ViewModel(){

    init {
        getListExchange()
    }

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

    fun getListExchange(){
        viewModelScope.launch(Dispatchers.IO) {
            exchangeUsecase.execute().collect {
                when(it){
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