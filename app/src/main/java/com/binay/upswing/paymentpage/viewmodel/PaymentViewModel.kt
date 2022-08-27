package com.binay.upswing.paymentpage.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binay.upswing.paymentpage.enums.PaymentStatus
import com.binay.upswing.paymentpage.repository.PaymentsRepository

class PaymentViewModel : ViewModel() {

    val paymentStatus: MutableLiveData<PaymentStatus> by lazy { MutableLiveData(PaymentStatus.PENDING) }

    init {
        pollPaymentStatus()
    }

    private fun pollPaymentStatus() {
        if (paymentStatus.value == PaymentStatus.PENDING) {
            fetchPaymentStatus()
        }
    }

    private fun fetchPaymentStatus() {
        val status = PaymentsRepository.fetchPaymentStatus()
        paymentStatus.postValue(status)
        pollPaymentStatus()
    }

}