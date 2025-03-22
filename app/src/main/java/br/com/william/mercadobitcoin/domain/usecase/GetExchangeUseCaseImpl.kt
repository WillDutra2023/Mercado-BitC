package br.com.william.mercadobitcoin.domain.usecase

import br.com.william.mercadobitcoin.core.UiState
import br.com.william.mercadobitcoin.core.toExchangeList
import br.com.william.mercadobitcoin.data.model.ErrorDTO
import br.com.william.mercadobitcoin.domain.model.ExchangeItem
import br.com.william.mercadobitcoin.domain.repositories.ExchangeRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetExchangeUseCaseImpl(private val exchangeRepo: ExchangeRepository) : GetExchangeUsecase {
    override suspend fun execute(): Flow<UiState<List<ExchangeItem>>> = flow {
        exchangeRepo.getProductList().collect { exchangeResponse ->
            when(exchangeResponse){
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