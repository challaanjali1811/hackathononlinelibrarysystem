package com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement.postpayment

import android.util.Log
import com.improve10x.hackathononlinelibrarymanagementsystem.bookmanagement.BookNetwork
import com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement.PaymentActivity

public class UpdateBookHandler(private val orderHandler: OrderHandler) :
    OrderHandler(orderHandler) {
    override fun processOrder(order: String?) {
        Log.d("UpdateBookHandler", "Processing payment for order: $order");
        updateBook()
        nextHandler?.processOrder(order)
    }

    private fun updateBook() {
        bookNetwork = BookNetwork.getInstance()
        val updatedBook = PaymentActivity.book!!.copy(
            unitsLeft = ((Integer.parseInt(PaymentActivity.book!!.unitsLeft)) - 1).toString(),
            unitsSold = ((Integer.parseInt(PaymentActivity.book!!.unitsSold)) + 1).toString()
        )
        bookNetwork?.updateBook(updatedBook)
        PaymentActivity.book = updatedBook
    }
}