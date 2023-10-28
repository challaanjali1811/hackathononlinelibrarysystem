package com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement.postpayment

import android.util.Log
import com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement.PaymentActivity
import com.improve10x.hackathononlinelibrarymanagementsystem.usermanagement.UserNetwork

public class AddBookToSellerHandler(private val orderHandler: OrderHandler) : OrderHandler(orderHandler) {
    override fun processOrder(order: String?) {
        Log.d("AddBookToSellerHandler", "Processing payment for order: $order");
        addBookToSeller()
        nextHandler?.processOrder(order)
    }

    private fun addBookToSeller() {
        userNetwork = UserNetwork.getInstance()
        val bookList = PaymentActivity.seller!!.books.toMutableList()
        bookList.add(PaymentActivity.book!!)
        val updatedUser = PaymentActivity.seller!!.copy(books = bookList)
        userNetwork?.updateUser(updatedUser)
    }
}