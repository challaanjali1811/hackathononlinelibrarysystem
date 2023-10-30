package com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement

import android.util.Log

internal class CreditCardPayment : PaymentStrategy() {
    override fun sendConfirmation() {
        Log.d("Online Library : Payment", "A confirmation email has been sent")
    }

    override fun makePayment(amount: Double) {
        Log.d("Online Library : Payment", "Processing Credit card payment of Rs. $amount")
    }

    override fun authenticate() {
        Log.d("Online Library : Payment", "Authentication of user is complete")
    }

}

