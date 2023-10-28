package com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement

import android.util.Log

internal class CreditCardPayment : PaymentStrategy {
    override fun processPayment(amount: Double) {
        Log.d("Payment", "Processing credit card payment of Rs. $amount")
    }
}

internal class UPIPayment : PaymentStrategy {
    override fun processPayment(amount: Double) {
        Log.d("Payment", "Processing upi payment of Rs. $amount")
    }
}

internal class NetBankingPayment : PaymentStrategy {
    override fun processPayment(amount: Double) {
        Log.d("Payment", "Processing net banking payment of Rs. $amount")
    }
}
