package com.example.cryptoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.R
import com.example.cryptoapp.model.Crypto
import kotlinx.android.synthetic.main.recycler_row.view.*

class CryptoAdapter(var cryptoList:ArrayList<Crypto>) :RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder>(){

    class CryptoViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_row,parent,false)
        println("creatviewholder")
        return CryptoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        holder.itemView.cryptoNameText.text=cryptoList[position].currency.toString()
        holder.itemView.cryptoPriceText.text=cryptoList[position].price.toString()
        println(cryptoList[position].currency.toString())
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