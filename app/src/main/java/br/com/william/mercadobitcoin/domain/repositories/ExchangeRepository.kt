package br.com.william.mercadobitcoin.domain.repositories

import br.com.william.mercadobitcoin.core.UiState
import br.com.william.mercadobitcoin.data.model.ExchangeListDTO
import br.com.william.mercadobitcoin.domain.model.ExchangeItem
import kotlinx.coroutines.flow.Flow

interface ExchangeRepository {
    suspend fun getProductList() :  Flow<UiState<List<ExchangeListDTO>>>
    suspend fun getProductDetail(assetId:String) :  Flow<UiState<List<ExchangeListDTO>>>
}