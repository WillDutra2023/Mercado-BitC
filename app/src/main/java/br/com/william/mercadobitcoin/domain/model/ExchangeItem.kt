package br.com.william.mercadobitcoin.domain.model

data class ExchangeItem(
    val assetId: String?,
    val chainAddresses: List<ChainAddress>?,
    val dataEnd: String?,
    val dataOrderbook_end: String?,
    val dataOrderbook_start: String?,
    val dataQuote_end: String?,
    val dataQuote_start: String?,
    val dataStart: String?,
    val dataSymbols_count: Int?,
    val dataTrade_end: String?,
    val dataTrade_start: String?,
    val idIcon: String?,
    val name: String?,
    val priceUsd: Double?,
    val supplyCurrent: Int?,
    val supplyMax: Int?,
    val supplyTotal: Int?,
    val type_isCrypto: Int?,
    val volume1Day_usd: Int?,
    val volume1Hrs_usd: Int?,
    val volume1Mth_usd: Int?
)

data class ChainAddress(
    val address: String?,
    val chainId: String?,
    val networkId: String?
)