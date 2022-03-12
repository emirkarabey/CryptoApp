package com.example.cryptoapp.view

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cryptoapp.R
import com.example.cryptoapp.adapter.CryptoAdapter
import com.example.cryptoapp.adapter.FavoriteAdapter
import com.example.cryptoapp.model.Crypto
import com.example.cryptoapp.viewmodel.CryptosViewModel
import com.example.cryptoapp.viewmodel.FavoritesViewModel
import kotlinx.android.synthetic.main.fragment_cryptos.*
import kotlinx.android.synthetic.main.fragment_favorites.*
import java.util.*


class FavoritesFragment : Fragment() {
    private lateinit var viewModel :FavoritesViewModel
    private val cryptoAdapter= FavoriteAdapter(arrayListOf())
    val args: FavoritesFragmentArgs by navArgs()
    private lateinit var tempArrayList : ArrayList<Crypto>
    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        tempArrayList = arrayListOf<Crypto>()
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = androidx.lifecycle.ViewModelProviders.of(this).get(FavoritesViewModel::class.java)
        val crypto = args.crypto
        if (crypto!=null){
            viewModel.addSQLite(crypto)
        }
        val delete= args.delete
        if (delete!=null){
            viewModel.deleteCrypto(delete)
        }
        viewModel.getDataFromSQLite()
        favorites_recyclerView.layoutManager = GridLayoutManager(context,2)
        favorites_recyclerView.adapter=cryptoAdapter
        observeLiveData()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_item,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.search_action){
            val searchView = item.actionView as SearchView
            searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
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
        }else if(item.itemId==R.id.spot){
            val action = FavoritesFragmentDirections.actionFavoritesFragmentToCryptosFragment()
            Navigation.findNavController(requireView()).navigate(action)
        }else if(item.itemId==R.id.favorites){
            val action = FavoritesFragmentDirections.actionFavoritesFragmentSelf(null,null)
            Navigation.findNavController(requireView()).navigate(action)
        }
        return super.onOptionsItemSelected(item)
    }

    fun observeLiveData(){
        viewModel.cryptos.observe(viewLifecycleOwner, Observer {crypto->
            crypto?.let {
                favorites_recyclerView.visibility=View.VISIBLE
                cryptoAdapter.cryptoListUpdate(it)
                cryptoAdapter.notifyDataSetChanged()
                favorites_progressBar.visibility=View.GONE
            }
        })

        viewModel.progressBar.observe(viewLifecycleOwner, Observer {
            if (it){
                favorites_recyclerView.visibility=View.GONE
                favorites_progressBar.visibility=View.VISIBLE
            }else{
                favorites_recyclerView.visibility=View.VISIBLE
                favorites_progressBar.visibility=View.GONE
            }
        })
    }
}