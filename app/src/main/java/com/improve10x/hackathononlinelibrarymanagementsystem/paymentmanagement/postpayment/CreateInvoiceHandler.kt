package com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement.postpayment

import android.util.Log
import com.improve10x.hackathononlinelibrarymanagementsystem.invoicemanagement.InvoiceBuilder
import com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement.PaymentActivity
import com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement.PaymentInfo

public class CreateInvoiceHandler(private val orderHandler: OrderHandler) : OrderHandler(orderHandler) {
    override fun processOrder(order: String?) {
        Log.d("CreateInvoiceHandler", "Processing payment for order: $order");
        createInvoice()
        nextHandler?.processOrder(order)
    }

    private fun createInvoice() {
        val paymentInfo = paymentInfo()
        val invoice = InvoiceBuilder()
            .setBuyerDetails(PaymentActivity.user)
            .setSellerDetails(PaymentActivity.seller)
            .setAppName("Online Library System")
            .setPurchaseDate(System.currentTimeMillis().toString())
            .setQuantity("1")
            .setOrderId("000")
            .setPaymentInfo(paymentInfo)
            .setBook(PaymentActivity.book)
            .build()
        PaymentActivity.invoice = invoice
        invoiceNetwork?.addInvoice(invoice)
    }

    private fun paymentInfo(): PaymentInfo {
        val paymentInfo = PaymentInfo(
            paymentMode = PaymentActivity.paymentStrategy,
            totalPrice = PaymentActivity.book!!.price,
            gstPrice = ((Integer.parseInt(PaymentActivity.book!!.price)) * 18 / 100).toString(),
            currency = "INR"
        )
        return paymentInfo
    }
}