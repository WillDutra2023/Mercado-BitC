package br.com.william.mercadobitcoin.presentation.contracts

import br.com.william.mercadobitcoin.domain.model.ExchangeItem

class ExchangeContract {
    data class State(
        val exchange: List<ExchangeItem> = listOf(),
        val isLoading: Boolean = false
    )
}