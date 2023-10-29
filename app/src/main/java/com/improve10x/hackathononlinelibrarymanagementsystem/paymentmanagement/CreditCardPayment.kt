package com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement

import android.util.Log

internal class CreditCardPayment : PaymentStrategy() {
    override fun sendConfirmation() {
        Log.d("Payment", "A confirmation email has been sent")
    }

    override fun makePayment(amount: Double) {
        Log.d("Payment", "Processing Credit card payment of Rs. $amount")
    }

    override fun authenticate() {
        Log.d("Payment", "Authentication of user is complete")
    }

}

internal class UPIPayment : PaymentStrategy() {
    override fun sendConfirmation() {
        Log.d("Payment", "A confirmation email has been sent")
    }

    override fun makePayment(amount: Double) {
        Log.d("Payment", "Processing upi payment of Rs. $amount")
    }

    override fun authenticate() {
        Log.d("Payment", "Authentication of user is complete")
    }

}

internal class NetBankingPayment : PaymentStrategy() {
    override fun sendConfirmation() {
        Log.d("Payment", "A confirmation email has been sent")
    }

    override fun makePayment(amount: Double) {
        Log.d("Payment", "Processing Net Banking payment of Rs. $amount")
    }

    override fun authenticate() {
        Log.d("Payment", "Authentication of user is complete")
    }
}
