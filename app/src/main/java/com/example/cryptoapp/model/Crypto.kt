package com.example.cryptoapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Crypto(
    @ColumnInfo(name = "currency")
    @SerializedName("currency")
    val currency :String?,
    @ColumnInfo(name = "price")
    @SerializedName("price")
    val price :String?
) {
    @PrimaryKey(autoGenerate = true)
    var uuid:Int =0
}