package com.example.cryptoapp.viewmodel


import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.adapter.CryptoAdapter
import com.example.cryptoapp.model.Crypto
import com.example.cryptoapp.service.CryptoAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CryptosViewModel :ViewModel(){
    val cryptos = MutableLiveData<List<Crypto>>()
    val progressBar = MutableLiveData<Boolean>()

    val cryptoApiService = CryptoAPIService()
    val disposable = CompositeDisposable()

    fun getDataFromInternet(){

        disposable.add(
            cryptoApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object:DisposableSingleObserver<List<Crypto>>(){
                    override fun onSuccess(t: List<Crypto>) {
                        cryptos.value=t
                        progressBar.value=false
                        println("veriler geldi")
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        println("veri gelmiyor")
                    }

                })
        )
    }

    fun showCryptos(cryptoList:List<Crypto>){
        progressBar.value=false
        cryptos.value=cryptoList
    }

    fun getCryptoList():MutableLiveData<List<Crypto>>{
        return cryptos
    }

}