package com.example.cryptoapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Crypto(
    @ColumnInfo(name = "currency")
    @SerializedName("currency")
    val currency :String?,
    @ColumnInfo(name = "price")
    @SerializedName("price")
    val price :String?,
    @ColumnInfo(name = "favori")
    var favorite:Boolean?
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var uuid:Int =0
}