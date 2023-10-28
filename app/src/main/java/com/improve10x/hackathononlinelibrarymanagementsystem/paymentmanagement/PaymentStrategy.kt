package com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement

internal interface PaymentStrategy {
    fun processPayment(amount: Double)
}