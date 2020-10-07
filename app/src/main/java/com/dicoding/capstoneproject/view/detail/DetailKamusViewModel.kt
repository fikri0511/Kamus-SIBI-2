package com.dicoding.capstoneproject.view.detail

import androidx.lifecycle.ViewModel
import com.dicoding.core.domain.model.Kamus
import com.dicoding.core.domain.usecase.KamusUseCase


class DetailKamusViewModel(private val kamusUseCase: KamusUseCase) : ViewModel() {
    fun setFavoriteKamus(kamus: Kamus, newStatus: Boolean) =
        kamusUseCase.setFavoriteKamus(kamus, newStatus)
}
