package com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement.postpayment

import android.util.Log
import com.improve10x.hackathononlinelibrarymanagementsystem.bookmanagement.Book
import com.improve10x.hackathononlinelibrarymanagementsystem.bookmanagement.BookNetwork
import com.improve10x.hackathononlinelibrarymanagementsystem.invoicemanagement.Invoice
import com.improve10x.hackathononlinelibrarymanagementsystem.usermanagement.UserInf

public class UpdateBookHandler(private val orderHandler: OrderHandler) :
    OrderHandler(orderHandler) {
    override fun processOrder(
        book: Book?,
        seller: UserInf,
        invoice: Invoice?,
        paymentStrategy: String?
    ) {
        Log.d("UpdateBookHandler", "Processing payment for order");
        val updatedBook = updateBook(book!!)
        nextHandler?.processOrder(updatedBook, seller, invoice, "CreditCard")
    }

    private fun updateBook(book : Book) : Book {
        bookNetwork = BookNetwork.getInstance()
        val updatedBook = book.copy(
            unitsLeft = ((Integer.parseInt(book.unitsLeft!!)) - 1).toString(),
            unitsSold = ((Integer.parseInt(book.unitsSold!!)) + 1).toString()
        )
        bookNetwork?.updateBook(updatedBook)
        return updatedBook
    }
}