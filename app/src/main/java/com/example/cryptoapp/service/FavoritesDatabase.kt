package com.example.cryptoapp.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cryptoapp.model.Crypto

@Database(entities = arrayOf(Crypto::class), version = 1)
abstract class FavoritesDatabase :RoomDatabase(){
    abstract fun favoritesDAO(): CryptoDAO

    companion object {
        @Volatile private var instance: FavoritesDatabase? = null
        private val lock = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            FavoritesDatabase::class.java,
            "favorites").build()
    }
}
