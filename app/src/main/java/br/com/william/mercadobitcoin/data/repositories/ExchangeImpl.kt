package br.com.william.mercadobitcoin.data.repositories

import br.com.william.mercadobitcoin.core.UiState
import br.com.william.mercadobitcoin.data.model.ErrorDTO
import br.com.william.mercadobitcoin.data.model.ExchangeListDTO
import br.com.william.mercadobitcoin.data.services.exchanges.ExchangeApiServiceHelper
import br.com.william.mercadobitcoin.domain.model.ExchangeItem
import br.com.william.mercadobitcoin.domain.repositories.ExchangeRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.catch
import org.koin.core.component.KoinComponent
import kotlinx.coroutines.flow.flow

class ExchangeImpl(
    private val exchangeApiService: ExchangeApiServiceHelper
) : ExchangeRepository, KoinComponent {
    override suspend fun getProductList() = flow<UiState<List<ExchangeListDTO>>> {
        emit(UiState.Loading())
        with(exchangeApiService.getAllExchangeListAPI()) {
            if (isSuccessful) {
                emit(UiState.Success(data = this.body()))
            } else {
                val errorDto = Gson().fromJson(this.errorBody()?.string(), ErrorDTO::class.java)
                emit(UiState.Error(message = errorDto.detail))
            }
        }
    }.catch {
        emit(UiState.Error(message = it.localizedMessage))
    }

    override suspend fun getProductDetail(assetId: String) = flow<UiState<List<ExchangeListDTO>>> {
        emit(UiState.Loading())
        with(exchangeApiService.getDetailExchangeListAPI(assetId)) {
            if (isSuccessful) {
                emit(UiState.Success(data = this.body()))
            } else {
                val errorDto = Gson().fromJson(this.errorBody()?.string(), ErrorDTO::class.java)
                emit(UiState.Error(message = errorDto.detail))
            }
        }
    }.catch {
        emit(UiState.Error(message = it.localizedMessage))
    }
}