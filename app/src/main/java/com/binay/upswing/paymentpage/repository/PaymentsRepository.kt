package com.binay.upswing.paymentpage.repository

import com.binay.upswing.paymentpage.enums.PaymentStatus

object PaymentsRepository {

    var count = 0

    fun fetchPaymentStatus(): PaymentStatus {
        if (count != 4) {
            count++
            return PaymentStatus.PENDING
        }
        return PaymentStatus.SUCCESS
    }

}