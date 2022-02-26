package com.example.cryptoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.R
import com.example.cryptoapp.adapter.CryptoAdapter
import com.example.cryptoapp.model.Crypto
import com.example.cryptoapp.viewmodel.CryptosViewModel
import kotlinx.android.synthetic.main.fragment_cryptos.*
import kotlinx.coroutines.delay


class CryptosFragment : Fragment() {
    private lateinit var viewModel : CryptosViewModel
    private val cryptoAdapter = CryptoAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = androidx.lifecycle.ViewModelProviders.of(this).get(CryptosViewModel::class.java)
        viewModel.getDataFromInternet()


        return inflater.inflate(R.layout.fragment_cryptos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = GridLayoutManager(context,2)
        recyclerView.adapter = cryptoAdapter
        observeLiveData()
    }
    fun observeLiveData(){
        viewModel.cryptos.observe(viewLifecycleOwner, Observer {crypto->
            crypto?.let {
                cryptoAdapter.cryptoListUpdate(it)
            }
        })
    }
}