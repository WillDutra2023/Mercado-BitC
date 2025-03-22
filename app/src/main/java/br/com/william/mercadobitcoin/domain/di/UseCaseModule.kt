package br.com.william.mercadobitcoin.domain.di

import br.com.william.mercadobitcoin.domain.usecase.GetExchangeDetailUseCase
import br.com.william.mercadobitcoin.domain.usecase.GetExchangeDetailUseCaseImpl
import br.com.william.mercadobitcoin.domain.usecase.GetExchangeUseCaseImpl
import br.com.william.mercadobitcoin.domain.usecase.GetExchangeUsecase
import org.koin.dsl.module

val useCaseModule = module {
    factory<GetExchangeUsecase> { GetExchangeUseCaseImpl(get()) }
    factory< GetExchangeDetailUseCase> { GetExchangeDetailUseCaseImpl(get()) }
}


