package com.dicoding.capstoneproject.view.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.core.domain.usecase.KamusUseCase


class MainMenuViewModel(kamusUseCase: KamusUseCase) : ViewModel() {
    val kamus = kamusUseCase.getAllKamus().asLiveData()
}