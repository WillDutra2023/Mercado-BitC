package br.com.william.mercadobitcoin.domain.usecase

import br.com.william.mercadobitcoin.core.UiState
import br.com.william.mercadobitcoin.domain.model.ExchangeItem
import kotlinx.coroutines.flow.Flow

interface GetExchangeDetailUseCase {
    suspend fun execute(assetId: String): Flow<UiState<List<ExchangeItem>>>
}