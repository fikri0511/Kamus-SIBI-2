package com.dicoding.core.data.remote.response

import com.google.gson.annotations.SerializedName


data class KamusResponse(
    @field:SerializedName("idkata")
    var idkata: String,
    @field:SerializedName("namakata")
    var namakata: String,
    @field:SerializedName("deskripsi")
    var deskripsi: String,
    @field:SerializedName("image")
    var image: String,
    @field:SerializedName("kategori")
    var kategori: String
)