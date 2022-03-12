package com.example.cryptoapp.viewmodel


import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.adapter.CryptoAdapter
import com.example.cryptoapp.model.Crypto
import com.example.cryptoapp.service.CryptoAPIService
import com.example.cryptoapp.service.CryptoDatabase
import com.example.cryptoapp.service.FavoritesDatabase
import com.example.cryptoapp.util.MySharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class CryptosViewModel(application: Application) :BaseViewModel(application){
    val cryptos = MutableLiveData<List<Crypto>>()
    val progressBar = MutableLiveData<Boolean>()
    private var updateTime = 10*60*1000*1000*1000L
    private val mySharedPreferences=MySharedPreferences()
    val cryptoApiService = CryptoAPIService()
    val disposable = CompositeDisposable()

    fun refreshData(){
        val saveTime = mySharedPreferences.getTime()
        if (saveTime!=null&&saveTime!=0L&&System.nanoTime()-saveTime<updateTime){
            getDataFromSQLite()
        }else{
            getDataFromInternet()
        }
    }

    fun getDataFromInternet(){
        progressBar.value=true
        disposable.add(
            cryptoApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object:DisposableSingleObserver<List<Crypto>>(){
                    override fun onSuccess(t: List<Crypto>) {
                        addSQLite(t)
                        Toast.makeText(getApplication(),"veriler internetten geldi",Toast.LENGTH_LONG).show()
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                })
        )
    }

    fun getDataFromSQLite(){
        progressBar.value=true
        launch {
            val dao = CryptoDatabase(getApplication()).cryptoDAO()
            val crypto = dao.getAllCrypto()
            Toast.makeText(getApplication(),"veriler sqlitan geldi",Toast.LENGTH_LONG).show()
            showCryptos(crypto)
        }
    }

    fun showCryptos(cryptoList:List<Crypto>){
        progressBar.value=false
        cryptos.value=cryptoList
    }

    fun addSQLite(cryptoList: List<Crypto>){
        launch {
            val dao = CryptoDatabase(getApplication()).cryptoDAO()
            dao.deleteAllCrypto()
            val uuidList=dao.insertAll(*cryptoList.toTypedArray())
            var i =0
            while(i<cryptoList.size){
                cryptoList[i].uuid = uuidList[i].toInt()
                i++
            }
            showCryptos(cryptoList)
        }
        mySharedPreferences.saveTime(System.nanoTime())
    }
}