package com.ginzburgworks.currencyconverter.view.rv_viewholder

import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ginzburgworks.currencyconverter.data.local.Coin
import com.ginzburgworks.currencyconverter.databinding.CoinItemBinding

class CoinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding: CoinItemBinding by lazy { getThisFilmItemBinding() }

    private fun getThisFilmItemBinding(): CoinItemBinding {
        return DataBindingUtil.bind(itemView) ?: CoinItemBinding.inflate(
            LayoutInflater.from(
                itemView.context
            )
        )
    }

    fun bind(coin: Coin) {
        binding.coin = coin
        binding.item = this
    }

    fun toggleUserInputVisibility() {
        if (binding.userInput.visibility == View.VISIBLE)
            hideUserCalculation()
        else
            showUserCalculation()
    }

    private fun hideUserCalculation() {
        hideUserInput()
        hideUserResult()
    }

    private fun showUserCalculation() {
        showUserInput()
        showUserResult()
    }

    private fun hideUserInput() {
        binding.userInput.visibility = View.GONE
    }

    private fun hideUserResult() {
        binding.userResult.visibility = View.GONE
    }

    private fun showUserInput() {
        binding.userInput.visibility = View.VISIBLE
    }

    private fun showUserResult() {
        binding.userResult.visibility = View.VISIBLE
    }


    fun updateResultOnUserInput(nominal: Int,value:Double) {
        val result: Double = if (binding.userInput.text.isEmpty())
            0.0
        else
            (binding.userInput.text.toString().toDouble() / value) * nominal
        showUserResult()
        binding.userResult.text = String.format("%.3f", result)
    }
}