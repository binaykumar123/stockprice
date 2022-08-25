package com.binay.upswing.ui.stocks.repository

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.*

object StocksRepository {

    @RequiresApi(Build.VERSION_CODES.N)
    fun fetchStockPrice(): Double {
        return Random().nextDouble()
    }
}