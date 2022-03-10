package com.example.cryptoapp.service

import com.example.cryptoapp.model.Crypto
import io.reactivex.Single
import retrofit2.http.GET

interface CryptoAPI {
    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    fun getCrypto(): Single<List<Crypto>>
}