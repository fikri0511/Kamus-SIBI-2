package com.dicoding.capstoneproject.di

import com.dicoding.core.domain.usecase.KamusInteractor
import com.dicoding.core.domain.usecase.KamusUseCase
import com.dicoding.capstoneproject.view.detail.DetailKamusViewModel
import com.dicoding.capstoneproject.view.main.MainMenuViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<KamusUseCase> { KamusInteractor(get()) }
}

val viewModelModule = module {

    viewModel { MainMenuViewModel(get()) }
    viewModel { DetailKamusViewModel(get()) }

}