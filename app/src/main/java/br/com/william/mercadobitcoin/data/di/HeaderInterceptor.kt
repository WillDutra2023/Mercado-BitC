package br.com.william.mercadobitcoin.data.di

import br.com.william.mercadobitcoin.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val reqOriginal = chain.request()

        val request = reqOriginal.newBuilder().header(
            "Authorization", BuildConfig.apikey
        ).build()

        return chain.proceed(request)
    }
}