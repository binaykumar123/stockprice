package com.binay.upswing.ui.stocks.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binay.upswing.ui.stocks.data.models.StockTrend
import com.binay.upswing.ui.stocks.repository.StocksRepository
import java.util.*

class StocksViewModel : ViewModel() {

    val stockPrice: MutableLiveData<Double> by lazy { MutableLiveData() }
    val stocksTrend: MutableLiveData<StockTrend> by lazy { MutableLiveData() }
    private val timer = Timer()

    init {
        startFetchingStockPrice()
    }

    private fun startFetchingStockPrice() {
        timer.scheduleAtFixedRate(
            object : TimerTask() {

                @RequiresApi(Build.VERSION_CODES.N)
                override fun run() {
                    fetchStockPrice()
                }

            }, 0, 5000
        )
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun fetchStockPrice() {
        val previousPrice = stockPrice.value
        val price = StocksRepository.fetchStockPrice()
        previousPrice?.let {
            if (price >= it) {
                stocksTrend.postValue(StockTrend.INCREASING)
            } else {
                stocksTrend.postValue(StockTrend.DECREASING)
            }
        }
        stockPrice.postValue(price)
    }

    override fun onCleared() {
        timer.cancel()
        super.onCleared()
    }

}