package br.com.william.mercadobitcoin.domain.di
import br.com.william.mercadobitcoin.presentation.features.exchange.viewModel.ExchangeViewModel
import br.com.william.mercadobitcoin.presentation.features.exchangeDetail.viewModel.ExchangeDetailViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ExchangeViewModel(get()) }
    viewModel { ExchangeDetailViewModel(get()) }


    viewModelOf(::ExchangeViewModel)
    viewModelOf(::ExchangeDetailViewModel)
    }