package com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement

internal object PaymentStrategyFactory {
    fun createPaymentStrategy(paymentMethod: String): PaymentStrategy {
        return when (paymentMethod) {
            "CreditCard" -> {
                CreditCardPayment()
            }
            "UPI" -> {
                UPIPayment()
            }
            "NetBanking" -> {
                NetBankingPayment()
            }
            else -> {
                CreditCardPayment()
            }
        }
    }
}