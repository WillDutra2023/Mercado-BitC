package br.com.william.mercadobitcoin.utils

import android.provider.Settings.Global.getString
import br.com.william.mercadobitcoin.BuildConfig
import br.com.william.mercadobitcoin.R

class Constant {
    companion object {
        internal const val BASE_URL = BuildConfig.baseUrl
        internal const val ID_ICON: String = "id_icon"
        internal const val ASSET_ID: String = "asset_id"
    }
}