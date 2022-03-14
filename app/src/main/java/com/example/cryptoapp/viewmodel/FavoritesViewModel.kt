package com.example.cryptoapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.cryptoapp.model.Crypto
import com.example.cryptoapp.service.FavoritesDatabase
import com.example.cryptoapp.view.FavoritesFragmentDirections
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch

class FavoritesViewModel(application: Application) : BaseViewModel(application){
    val cryptos = MutableLiveData<List<Crypto>>()
    val progressBar = MutableLiveData<Boolean>()


    fun addSQLite(crypto:Crypto){
        progressBar.value=true
        launch {
            val dao = FavoritesDatabase(getApplication()).favoritesDAO()
            dao.insertAll(crypto)
            getDataFromSQLite()
        }
    }

    fun getDataFromSQLite(){
        progressBar.value=true
        launch {
            val dao = FavoritesDatabase(getApplication()).favoritesDAO()
            val crypto = dao.getAllCrypto()
            showCryptos(crypto)
        }

    }
    fun showCryptos(cryptoList:List<Crypto>){
        progressBar.value=false
        cryptos.value=cryptoList
    }

    fun deleteCrypto(crypto: Crypto){
        progressBar.value=true
        launch {
            val dao = FavoritesDatabase(getApplication()).favoritesDAO()
            dao.deleteCrypto(crypto.uuid)
        }
        getDataFromSQLite()
        //uuidsi favorilerde olanların image buttonunu kalp yap

    }
}