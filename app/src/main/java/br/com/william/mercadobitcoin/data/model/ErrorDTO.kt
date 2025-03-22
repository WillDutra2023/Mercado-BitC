package br.com.william.mercadobitcoin.data.model

data class ErrorDTO(
    val QuotaKey: String,
    val QuotaName: String,
    val QuotaType: String,
    val QuotaValue: Int,
    val QuotaValueAdjustable: String,
    val QuotaValueCurrentUsage: Int,
    val QuotaValueUnit: String,
    val detail: String,
    val error: String,
    val status: Int,
    val title: String
)