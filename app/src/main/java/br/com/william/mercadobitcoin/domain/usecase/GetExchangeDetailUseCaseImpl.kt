package br.com.william.mercadobitcoin.domain.usecase

import br.com.william.mercadobitcoin.core.UiState
import br.com.william.mercadobitcoin.core.toExchangeList
import br.com.william.mercadobitcoin.domain.model.ExchangeItem
import br.com.william.mercadobitcoin.domain.repositories.ExchangeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetExchangeDetailUseCaseImpl(private val exchangeRepo: ExchangeRepository) :
    GetExchangeDetailUseCase {
    override suspend fun execute(assetId: String): Flow<UiState<List<ExchangeItem>>> = flow {
        exchangeRepo.getProductDetail(assetId).collect { exchangeResponse ->
            when (exchangeResponse) {
                is UiState.Success<*> -> {
                    val exchangeDataList = exchangeResponse.data?.map { exchange ->
                        exchange.toExchangeList()
                    }
                    emit(UiState.Success(data = exchangeDataList))
                }

                is UiState.Error<*> -> {
                    emit(UiState.Error(message = exchangeResponse.message))
                }

                is UiState.Loading<*> -> {
                    emit(UiState.Loading())
                }
            }
        }
    }
}