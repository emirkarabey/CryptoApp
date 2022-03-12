package com.example.cryptoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.R
import com.example.cryptoapp.model.Crypto
import com.example.cryptoapp.view.FavoritesFragmentDirections
import kotlinx.android.synthetic.main.recycler_row_2.view.*

class FavoriteAdapter (var cryptoList:ArrayList<Crypto>) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>(){
    class FavoriteViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_row_2,parent,false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.itemView.fav_cryptoNameText.text=cryptoList[position].currency
        holder.itemView.fav_cryptoPriceText.text=cryptoList[position].price
        holder.itemView.fav_fav_btn.setOnClickListener {
            val action = FavoritesFragmentDirections.actionFavoritesFragmentSelf(null,cryptoList[position])
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