package com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement

interface PaymentStrategy {
    fun processPayment(amount: Double)
}