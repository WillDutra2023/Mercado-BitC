package br.com.william.mercadobitcoin.data.services.exchanges

import br.com.william.mercadobitcoin.data.model.ExchangeListDTO
import retrofit2.Response

class ExchangeApiHelperImpl(private val service: ExchangeService) : ExchangeApiServiceHelper {

    override suspend fun getAllExchangeListAPI(): Response<List<ExchangeListDTO>> =
        service.getAllExchangeListAPI()

    override suspend fun getDetailExchangeListAPI(assetId: String): Response<List<ExchangeListDTO>> =
        service.getDetailExchangeListAPI(assetId)
}