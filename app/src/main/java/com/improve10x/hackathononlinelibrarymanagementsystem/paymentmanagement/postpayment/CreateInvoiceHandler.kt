package com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement.postpayment

import android.util.Log
import com.improve10x.hackathononlinelibrarymanagementsystem.bookmanagement.Book
import com.improve10x.hackathononlinelibrarymanagementsystem.invoicemanagement.Invoice
import com.improve10x.hackathononlinelibrarymanagementsystem.invoicemanagement.InvoiceBuilder
import com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement.PaymentInfo
import com.improve10x.hackathononlinelibrarymanagementsystem.usermanagement.UserInf
import com.improve10x.hackathononlinelibrarymanagementsystem.usermanagement.UserMgr

public class CreateInvoiceHandler(private val orderHandler: OrderHandler) : OrderHandler(orderHandler) {
    override fun processOrder(
        book: Book?,
        seller: UserInf,
        invoice: Invoice?,
        paymentStrategy: String?
    ) {
        Log.d("Online Library : CreateInvoiceHandler", "Processing payment for order");
        val invoice = createInvoice(book, seller,paymentStrategy!!)
        nextHandler?.processOrder(book, seller, invoice, paymentStrategy)
    }

    private fun createInvoice(book: Book?, seller: UserInf, paymentStrategy: String) :Invoice {
        val paymentInfo = paymentInfo(book, paymentStrategy)
        val invoice = InvoiceBuilder()
            .setBuyerDetails(UserMgr.getCurrentUser()?.createNestedObject())
            .setSellerDetails(seller.createNestedObject())
            .setAppName("Online Library System")
            .setPurchaseDate(System.currentTimeMillis().toString())
            .setQuantity("1")
            .setOrderId("000")
            .setPaymentInfo(paymentInfo)
            .setBook(book)
            .build()
        invoiceNetwork?.addInvoice(invoice)
        return invoice
    }

    private fun paymentInfo(book: Book?, paymentStrategy: String): PaymentInfo {
        return PaymentInfo(
            paymentMode = paymentStrategy,
            totalPrice = book!!.price,
            gstPrice = ((Integer.parseInt(book!!.price)) * 18 / 100).toString(),
            currency = "INR"
        )
    }
}