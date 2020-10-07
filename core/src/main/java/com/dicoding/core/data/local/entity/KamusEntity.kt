package com.dicoding.core.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kamus")
data class KamusEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "idkata")
    var idkata: String,

    @ColumnInfo(name = "namakata")
    var namakata: String,

    @ColumnInfo(name = "deskripsi")
    var deskripsi: String,

    @ColumnInfo(name = "kategori")
    var kategori: String,

    @ColumnInfo(name = "image")
    var image: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)