package com.example.cryptoapp.service

import com.example.cryptoapp.model.Crypto
import io.reactivex.Single
import retrofit2.http.GET

interface CryptoAPI {
    @GET("prices?key=91c5fc1f99664950c9fa54074cb68d65e63c2bd1")
    fun getCrypto(): Single<List<Crypto>>
}