package com.ginzburgworks.currencyconverter.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ginzburgworks.currencyconverter.databinding.ActivityMainBinding
import com.ginzburgworks.currencyconverter.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewModel = viewModel
        initRecycler()
        subscribeToDataUpdates()
    }

    private fun subscribeToDataUpdates() {
        viewModel.coinsListLiveData.observe(this) {
            viewModel.coinsAdapter.refreshAdapterData(it)
        }
    }

    private fun initRecycler() {
        binding.mainRecycler.apply {
            adapter = viewModel.coinsAdapter
            layoutManager = LinearLayoutManager(MainActivity())
        }.also {
            viewModel.coinsAdapter.onItemClick = {
                viewModel.coinsAdapter.updateCalculationFlag(it)
            }
        }
    }
}


