package com.ginzburgworks.currencyconverter.view.rv_adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.ginzburgworks.currencyconverter.data.local.Coin
import com.ginzburgworks.currencyconverter.databinding.CoinItemBinding
import com.ginzburgworks.currencyconverter.view.rv_viewholder.CoinViewHolder
import kotlinx.android.synthetic.main.coin_item.view.*

class CoinListRecyclerAdapter() :
    RecyclerView.Adapter<CoinViewHolder>() {

    var onItemClick: ((Coin) -> Unit)? = null

    private val items = mutableListOf<Coin>()

    private lateinit var binding: CoinItemBinding

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = CoinItemBinding.inflate(inflater, parent, false)
        return CoinViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val item = items[holder.adapterPosition]
        if (item.calculationIsOn) {
            holder.itemView.user_input.visibility = View.VISIBLE
            holder.itemView.user_result.visibility = View.VISIBLE
        } else {
            holder.itemView.user_input.visibility = View.GONE
            holder.itemView.user_result.visibility = View.GONE
        }
        holder.bind(item)
        holder.itemView.setOnClickListener { onItemClick?.invoke(item) }
    }

    fun refreshAdapterData(list: List<Coin>) {
        if (list.isEmpty())
            return
        clearItems()
        addItems(list)
    }

    private fun addItems(list: List<Coin>) {
        val itemCountBeforeAdding = itemCount
        val itemsAdded = list.size
        items.addAll(list)
        notifyItemRangeInserted(itemCountBeforeAdding, itemsAdded)
        notifyItemRangeChanged(0, itemsAdded)
    }

    private fun clearItems() {
        val itemCountBeforeClear = itemCount
        items.clear()
        notifyItemRangeRemoved(0, itemCountBeforeClear)
    }

    fun updateCalculationFlag(coin: Coin) {
        clearCalculationFlag()
        clearUserInput()
        setCalculationFlag(coin)
    }

    private fun clearCalculationFlag() {
        items.forEachIndexed { index, it ->
            if (it.calculationIsOn) {
                it.calculationIsOn = false
                notifyItemChanged(index)
            }
        }
    }

    private fun clearUserInput() {
        binding.userInput.text.clear()
    }

    private fun setCalculationFlag(coin: Coin) {
        items.forEachIndexed { index, it ->
            if (it.charCode == coin.charCode) {
                it.calculationIsOn = true
                notifyItemChanged(index)
            }
        }
    }

}
