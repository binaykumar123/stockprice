package com.binay.upswing

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.binay.upswing.databinding.ActivityMainBinding
import com.binay.upswing.ui.stocks.data.models.StockTrend
import com.binay.upswing.ui.stocks.viewmodel.StocksViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var stocksViewModel: StocksViewModel
    private lateinit var stocksPriceTV: TextView
    private lateinit var stocksTrendTV: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        observeValues()
    }

    private fun init() {
        stocksViewModel = ViewModelProvider(this).get(StocksViewModel::class.java)
        stocksPriceTV = findViewById(R.id.stockPriceTV)
        stocksTrendTV = findViewById(R.id.stockTrendTV)
    }

    private fun observeValues() {
        stocksViewModel.stockPrice.observe(this) {
            it?.let {
                stocksPriceTV.text = it.toString()
            }
        }

        stocksViewModel.stocksTrend.observe(this) {
            it?.let {
                if (it == StockTrend.DECREASING) {
                    stocksTrendTV.text = "DOWN"
                } else {
                    stocksTrendTV.text = "UP"
                }
            }
        }
    }
}