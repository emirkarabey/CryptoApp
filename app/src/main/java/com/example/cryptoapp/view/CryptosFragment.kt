package com.example.cryptoapp.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.R
import com.example.cryptoapp.adapter.CryptoAdapter
import com.example.cryptoapp.model.Crypto
import com.example.cryptoapp.viewmodel.CryptosViewModel
import kotlinx.android.synthetic.main.fragment_cryptos.*
import kotlinx.coroutines.delay
import java.util.*
import kotlin.collections.ArrayList


class CryptosFragment : Fragment() {
    private lateinit var viewModel : CryptosViewModel
    private val cryptoAdapter = CryptoAdapter(arrayListOf())
    private lateinit var tempArrayList : ArrayList<Crypto>
    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        tempArrayList = arrayListOf<Crypto>()
        super.onCreate(savedInstanceState)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_item,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                tempArrayList.clear()
                val searchText = p0!!.toLowerCase(Locale.getDefault())
                if (searchText.isNotEmpty()){
                    viewModel.cryptos.value!!.forEach {
                        if (it.toString().toLowerCase(Locale.getDefault()).contains(searchText)){
                            tempArrayList.add(it)
                        }
                    }
                    cryptoAdapter.cryptoListUpdate(tempArrayList)
                }else{
                    observeLiveData()
                }
                return false
            }

        })
        return super.onOptionsItemSelected(item)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment



        return inflater.inflate(R.layout.fragment_cryptos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = androidx.lifecycle.ViewModelProviders.of(this).get(CryptosViewModel::class.java)
        viewModel.refreshData()
        recyclerView.layoutManager = GridLayoutManager(context,2)
        recyclerView.adapter = cryptoAdapter
        observeLiveData()
    }
    fun observeLiveData(){
        viewModel.cryptos.observe(viewLifecycleOwner, Observer {crypto->
            crypto?.let {
                recyclerView.visibility=View.VISIBLE
                cryptoAdapter.cryptoListUpdate(it)
                cryptoAdapter.notifyDataSetChanged()
                progressBar.visibility=View.GONE
            }
        })

        viewModel.progressBar.observe(viewLifecycleOwner, Observer {
            if (it){
                recyclerView.visibility=View.GONE
                progressBar.visibility=View.VISIBLE
            }else{
                recyclerView.visibility=View.VISIBLE
                progressBar.visibility=View.GONE
            }
        })
    }
}