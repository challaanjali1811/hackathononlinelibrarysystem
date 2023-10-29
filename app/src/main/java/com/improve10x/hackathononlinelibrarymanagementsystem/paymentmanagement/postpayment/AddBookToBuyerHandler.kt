package com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement.postpayment

import android.util.Log
import com.improve10x.hackathononlinelibrarymanagementsystem.bookmanagement.Book
import com.improve10x.hackathononlinelibrarymanagementsystem.invoicemanagement.Invoice
import com.improve10x.hackathononlinelibrarymanagementsystem.usermanagement.UserInf
import com.improve10x.hackathononlinelibrarymanagementsystem.usermanagement.UserNetwork

public class AddBookToBuyerHandler(private val orderHandler: OrderHandler) : OrderHandler(orderHandler) {
    override fun processOrder(
        book: Book?,
        seller: UserInf,
        invoice: Invoice?,
        paymentStrategy: String?
    ) {
        Log.d("AddBookToBuyerHandler", "Processing payment for order");
        addBookToBuyer(book!!)
        nextHandler?.processOrder(book, seller, invoice, paymentStrategy)
    }

    private fun addBookToBuyer(book : Book) {
        userNetwork = UserNetwork.getInstance()
        val bookList = user!!.books.toMutableList()
        bookList.add(book)
        val updatedUser = user!!.copy(books = bookList)
        userNetwork?.updateUser(updatedUser)
    }
}