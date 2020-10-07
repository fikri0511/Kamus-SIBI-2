package com.dicoding.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.core.domain.usecase.KamusUseCase

class FavoriteViewModel(kamusUseCase: KamusUseCase) : ViewModel() {
    val favoriteKamus = kamusUseCase.getFavoriteKamus().asLiveData()
}