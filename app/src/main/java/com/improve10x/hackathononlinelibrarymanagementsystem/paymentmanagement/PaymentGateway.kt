package com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement

import android.util.Log
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock


class PaymentGatewayManager private constructor() {
    init {
        Log.d("Online Library : Payment Manager","Payment Gateway Manager initialized.")
    }

    fun processPayment(amount: Double) {
        Log.d("Online Library : Payment Manager","Processing payment of Rs. $amount through the payment gateway.")
    }

    companion object {
        var instance: PaymentGatewayManager? = null
            get() {
                if (field == null) {
                    mtx.lock()
                    try {
                        if (field == null) {
                            field = PaymentGatewayManager()
                        }
                    } finally {
                        mtx.unlock()
                    }
                }
                return field
            }
            private set
        private val mtx: Lock = ReentrantLock()
    }
}
