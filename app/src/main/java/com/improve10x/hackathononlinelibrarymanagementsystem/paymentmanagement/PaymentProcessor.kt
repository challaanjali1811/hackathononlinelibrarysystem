package com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement

internal class PaymentProcessor {
    private var paymentStrategy: PaymentStrategy? = null
    fun setPaymentStrategy(paymentMethod: String?) {
        if (paymentStrategy != null) {
            paymentStrategy = null
        }
        paymentStrategy = PaymentStrategyFactory.createPaymentStrategy(paymentMethod!!)
    }

    fun processPayment(amount: Double) {
        if (paymentStrategy != null) {
            paymentStrategy!!.processPayment(amount)
        } else {
            System.err.println("Payment strategy not set.")
        }
    }
}
