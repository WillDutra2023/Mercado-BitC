package br.com.william.mercadobitcoin.data.services.exchanges

import br.com.william.mercadobitcoin.data.model.ExchangeListDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ExchangeService {
    @GET("/v1/assets")
    suspend fun getAllExchangeListAPI(@Query("filter_asset_id") filter: String = "OXY;POLIS;HUOGUO"): Response<List<ExchangeListDTO>>
    @GET("/v1/assets/{asset_id}")
    suspend fun getDetailExchangeListAPI(@Path("asset_id") assetId: String): Response<List<ExchangeListDTO>>
}