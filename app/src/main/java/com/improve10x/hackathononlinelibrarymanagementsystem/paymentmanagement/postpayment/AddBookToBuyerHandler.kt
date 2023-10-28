package com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement.postpayment

import android.util.Log
import com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement.PaymentActivity
import com.improve10x.hackathononlinelibrarymanagementsystem.usermanagement.UserNetwork

public class AddBookToBuyerHandler(private val orderHandler: OrderHandler) : OrderHandler(orderHandler) {
    override fun processOrder(order: String?) {
        Log.d("AddBookToBuyerHandler", "Processing payment for order: $order");
        addBookToBuyer()
        nextHandler?.processOrder(order)
    }

    private fun addBookToBuyer() {
        userNetwork = UserNetwork.getInstance()
        val bookList = user!!.books.toMutableList()
        bookList.add(PaymentActivity.book!!)
        val updatedUser = user!!.copy(books = bookList)
        userNetwork?.updateUser(updatedUser)
    }
}