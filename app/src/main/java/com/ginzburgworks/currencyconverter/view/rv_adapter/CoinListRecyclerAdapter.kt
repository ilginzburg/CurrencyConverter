package com.ginzburgworks.currencyconverter.view.rv_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ginzburgworks.currencyconverter.data.local.Coin
import com.ginzburgworks.currencyconverter.databinding.CoinItemBinding
import com.ginzburgworks.currencyconverter.view.rv_viewholder.CoinViewHolder

class CoinListRecyclerAdapter : RecyclerView.Adapter<CoinViewHolder>() {

    private val items = mutableListOf<Coin>()

    private lateinit var binding: CoinItemBinding

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = CoinItemBinding.inflate(inflater, parent, false)
        return CoinViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.bind(items[holder.adapterPosition],binding)
    }

    fun addItems(list: List<Coin>) {
        val itemCountBeforeAdding = itemCount
        val itemsAdded = list.size
        items.addAll(list)
        notifyItemRangeInserted(itemCountBeforeAdding, itemsAdded)
        notifyItemRangeChanged(0, itemsAdded)
    }

    fun clearItems() {
        val itemCountBeforeClear = itemCount
        items.clear()
        notifyItemRangeRemoved(0, itemCountBeforeClear)
    }

}