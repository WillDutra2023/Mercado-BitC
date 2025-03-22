package br.com.william.mercadobitcoin.data.services.exchanges

import br.com.william.mercadobitcoin.data.model.ExchangeListDTO
import retrofit2.Response

interface ExchangeApiServiceHelper {
    suspend fun getAllExchangeListAPI(): Response<List<ExchangeListDTO>>
    suspend fun getDetailExchangeListAPI(assetId: String): Response<List<ExchangeListDTO>>
}