package com.example.cryptoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.R
import com.example.cryptoapp.model.Crypto
import com.example.cryptoapp.view.CryptosFragmentDirections
import kotlinx.android.synthetic.main.recycler_row.view.*
import kotlin.coroutines.coroutineContext

class CryptoAdapter(var cryptoList:ArrayList<Crypto>) :RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder>(){

    class CryptoViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_row,parent,false)
        return CryptoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        holder.itemView.cryptoNameText.text=cryptoList[position].currency.toString()
        holder.itemView.cryptoPriceText.text=cryptoList[position].price.toString()
        if (cryptoList[position].favorite==true){
            holder.itemView.fav_btn.setBackgroundResource(R.drawable.ic_baseline_favorite_clicked_24)
            //println("bu favorilere eklenmiş"+cryptoList[position].currency)
        }
        holder.itemView.fav_btn.setOnClickListener{
            it.setBackgroundResource(R.drawable.ic_baseline_favorite_clicked_24)
            cryptoList[position].favorite=true
            val action = CryptosFragmentDirections.actionCryptosFragmentToFavoritesFragment(cryptoList[position],null)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return cryptoList.size
    }

    fun cryptoListUpdate(newList:List<Crypto>){
        cryptoList.clear()
        cryptoList.addAll(newList)
        notifyDataSetChanged()
    }
}