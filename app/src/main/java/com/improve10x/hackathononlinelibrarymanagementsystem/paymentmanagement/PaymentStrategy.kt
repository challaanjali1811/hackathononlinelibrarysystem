package com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement

abstract class PaymentStrategy {
    private val paymentGateway = PaymentGatewayManager.instance
    fun processPayment(amount: Double) {
        authenticate()
        paymentGateway!!.processPayment(amount)
        sendConfirmation()
    }

    abstract fun sendConfirmation()

    abstract fun makePayment(amount: Double)

    abstract fun authenticate()

}