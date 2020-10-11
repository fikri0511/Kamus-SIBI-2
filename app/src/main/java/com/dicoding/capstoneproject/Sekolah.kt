package com.dicoding.capstoneproject

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Sekolah(
    val name: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val image : Int
) : Parcelable
