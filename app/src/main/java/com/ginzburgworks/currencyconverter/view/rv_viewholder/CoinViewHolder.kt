package com.ginzburgworks.currencyconverter.view.rv_viewholder

import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ginzburgworks.currencyconverter.data.local.Coin
import com.ginzburgworks.currencyconverter.databinding.CoinItemBinding
import kotlinx.android.synthetic.main.coin_item.view.*

class CoinViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    private val binding: CoinItemBinding =
        DataBindingUtil.bind(itemView) ?: CoinItemBinding.inflate(
            LayoutInflater.from(
                itemView.context
            )
        )

    fun bind(coin: Coin) {
        binding.coin = coin
        binding.item = this
    }

    fun updateResultOnUserInput(nominal: Int, value: Double) {
        val result: Double = if (itemView.user_input.text.isEmpty())
            0.0
        else
            (itemView.user_input.text.toString().toDouble() / value) * nominal
        itemView.user_result.text= String.format("%.3f", result)
    }

}