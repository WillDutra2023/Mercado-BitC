package br.com.william.mercadobitcoin.data.model

data class ExchangeListDTO(
    val asset_id: String?,
    val chain_addresses: List<ChainAddressDTO>?,
    val data_end: String?,
    val data_orderbook_end: String?,
    val data_orderbook_start: String?,
    val data_quote_end: String?,
    val data_quote_start: String?,
    val data_start: String?,
    val data_symbols_count: Int?,
    val data_trade_end: String?,
    val data_trade_start: String?,
    val id_icon: String?,
    val name: String?,
    val price_usd: Double?,
    val supply_current: Int?,
    val supply_max: Int?,
    val supply_total: Int?,
    val type_is_crypto: Int?,
    val volume_1day_usd: Int?,
    val volume_1hrs_usd: Int?,
    val volume_1mth_usd: Int?
)

data class ChainAddressDTO(
    val address: String?,
    val chain_id: String?,
    val network_id: String?
)