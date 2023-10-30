package com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement.postpayment

import android.util.Log
import com.improve10x.hackathononlinelibrarymanagementsystem.bookmanagement.Book
import com.improve10x.hackathononlinelibrarymanagementsystem.invoicemanagement.Invoice
import com.improve10x.hackathononlinelibrarymanagementsystem.usermanagement.UserInf
import com.improve10x.hackathononlinelibrarymanagementsystem.usermanagement.UserNetwork

public class AddBookToSellerHandler(private val orderHandler: OrderHandler) : OrderHandler(orderHandler) {
    override fun processOrder(
        book: Book?,
        seller: UserInf,
        invoice: Invoice?,
        paymentStrategy: String?
    ) {
        Log.d("Online Library : AddBookToSellerHandler", "Processing payment for order");
        val updatedSeller = addBookToSeller(book, seller)
        nextHandler?.processOrder(book, updatedSeller, invoice, paymentStrategy)
    }

    private fun addBookToSeller(book: Book?, seller: UserInf) : UserInf {
        userNetwork = UserNetwork.getInstance()
        val bookList = seller.books.toMutableList()
        bookList.add(book!!)
        val updatedUser = seller.copy(books = bookList)
        userNetwork?.updateUser(updatedUser)
        return updatedUser
    }
}