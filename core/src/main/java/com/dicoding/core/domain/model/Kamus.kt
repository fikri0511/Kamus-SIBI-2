package com.dicoding.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Kamus(
    var idkata:String,
    var namakata:String,
    var deskripsi:String,
    var image:String,
    var kategori:String,
    var isFavorite: Boolean = false

):Parcelable