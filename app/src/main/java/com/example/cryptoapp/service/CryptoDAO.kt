package com.example.cryptoapp.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.cryptoapp.model.Crypto

@Dao
interface CryptoDAO {
    @Insert
    suspend fun insertAll(vararg crypto : Crypto):List<Long>
    @Query("SELECT * FROM crypto")
    suspend fun getAllCrypto():List<Crypto>
    @Query("SELECT * FROM crypto WHERE uuid=:cryptoID")
    suspend fun getCrypto(cryptoID : Int):Crypto
    @Query("DELETE FROM crypto")
    suspend fun deleteAllCrypto()
    @Query("DELETE FROM crypto WHERE uuid=:cryptoID")
    suspend fun deleteCrypto(cryptoID: Int)
}