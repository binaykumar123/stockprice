package com.binay.upswing.paymentpage.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.binay.upswing.R
import com.binay.upswing.paymentpage.enums.PaymentStatus
import com.binay.upswing.paymentpage.viewmodel.PaymentViewModel

class PaymentPage : AppCompatActivity() {

    private lateinit var loadingProgressBar: ProgressBar
    private lateinit var statusTV: TextView
    private lateinit var paymentViewModel: PaymentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_page)
        init()
    }

    private fun init() {
        loadingProgressBar = findViewById(R.id.loadingProgressBar)
        statusTV = findViewById(R.id.paymentStatusTV)
        paymentViewModel = ViewModelProvider(this).get(PaymentViewModel::class.java)
        observeValues()
    }

    private fun observeValues() {
        paymentViewModel.paymentStatus.observe(this) {
            it?.let { paymentStatus ->
                when (paymentStatus) {
                    PaymentStatus.PENDING -> {
                        statusTV.text = "Fetching Payment Status"

                    }
                    PaymentStatus.SUCCESS -> {
                        loadingProgressBar.visibility = View.GONE
                        statusTV.text = "Your Payment is Successful"
                    }
                    PaymentStatus.FAILURE -> {
                        loadingProgressBar.visibility = View.GONE
                        statusTV.text = "Payment Failed"
                    }
                }
            }
        }
    }
}