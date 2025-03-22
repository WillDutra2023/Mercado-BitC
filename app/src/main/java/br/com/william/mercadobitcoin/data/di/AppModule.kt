package br.com.william.mercadobitcoin.data.di

import br.com.william.mercadobitcoin.data.repositories.ExchangeImpl
import br.com.william.mercadobitcoin.data.services.exchanges.ExchangeApiHelperImpl
import br.com.william.mercadobitcoin.data.services.exchanges.ExchangeApiServiceHelper
import br.com.william.mercadobitcoin.domain.repositories.ExchangeRepository
import br.com.william.mercadobitcoin.presentation.features.exchangeDetail.view.ExchangeDetail
import org.koin.dsl.module

val repositories = module {
    single<ExchangeRepository> { ExchangeImpl(get()) }
}

val serviceHelperModule = module {
    factory<ExchangeApiServiceHelper> { ExchangeApiHelperImpl(get()) }
}
