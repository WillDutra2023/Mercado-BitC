package br.com.william.mercadobitcoin.core

import br.com.william.mercadobitcoin.data.model.ChainAddressDTO
import br.com.william.mercadobitcoin.data.model.ExchangeListDTO
import br.com.william.mercadobitcoin.domain.model.ChainAddress
import br.com.william.mercadobitcoin.domain.model.ExchangeItem

fun ExchangeListDTO.toExchangeList(): ExchangeItem {
    return ExchangeItem(
        assetId = this.asset_id,
        chainAddresses = this.chain_addresses?.toChainAddressItem(),
        dataEnd = this.data_end,
        dataOrderbook_end = this.data_orderbook_end,
        dataOrderbook_start = this.data_orderbook_start,
        dataQuote_end = this.data_quote_end,
        dataQuote_start = this.data_quote_start,
        dataStart = this.data_start,
        dataSymbols_count = this.data_symbols_count,
        dataTrade_end = this.data_end,
        dataTrade_start = this.data_trade_start,
        idIcon = this.id_icon,
        name = this.name,
        priceUsd = this.price_usd,
        supplyCurrent = this.supply_current,
        supplyMax = this.supply_max,
        supplyTotal = this.supply_total,
        type_isCrypto = this.type_is_crypto,
        volume1Day_usd = this.volume_1day_usd,
        volume1Hrs_usd = this.volume_1hrs_usd,
        volume1Mth_usd = this.volume_1mth_usd
    )
}

fun List<ChainAddressDTO>.toChainAddressItem(): List<ChainAddress> {
    val listExchangeItem = mutableListOf<ChainAddress>()
    this.map { item ->
        listExchangeItem.add(
            ChainAddress(
                address = item.address,
                chainId = item.chain_id,
                networkId = item.network_id
            )
        )
    }
    return listExchangeItem
}

