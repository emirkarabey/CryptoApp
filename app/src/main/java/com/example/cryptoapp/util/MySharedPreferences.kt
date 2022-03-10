package com.example.cryptoapp.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class MySharedPreferences {

    companion object{
        private val ZAMAN = "zaman"

        private var sharedPreferences : SharedPreferences?=null
        @Volatile private var instance :MySharedPreferences?=null
        private val lock=Any()
        operator fun invoke(context: Context):MySharedPreferences = instance ?: synchronized(lock){
            instance?:makeMySharedPreferences(context).also {
                instance=it
            }
        }
        fun makeMySharedPreferences(context: Context):MySharedPreferences{
            sharedPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
            return MySharedPreferences()
        }
    }
    fun saveTime(zaman:Long){
        sharedPreferences?.edit(commit = true){
            putLong(ZAMAN,zaman)
        }
    }
    fun getTime()= sharedPreferences?.getLong(ZAMAN,0)

}