package com.dicoding.kamus.maps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.core.domain.usecase.KamusUseCase

class MapsViewModel(kamusUseCase: KamusUseCase) : ViewModel() {
    val kamus = kamusUseCase.getAllKamus().asLiveData()
}