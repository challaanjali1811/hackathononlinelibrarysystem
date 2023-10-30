package com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement.postpayment

import android.util.Log
import com.improve10x.hackathononlinelibrarymanagementsystem.bookmanagement.Book
import com.improve10x.hackathononlinelibrarymanagementsystem.invoicemanagement.Invoice
import com.improve10x.hackathononlinelibrarymanagementsystem.usermanagement.UserInf
import com.improve10x.hackathononlinelibrarymanagementsystem.usermanagement.UserNetwork

public class AddInvoiceToSellerHandler(private val orderHandler: OrderHandler?) : OrderHandler(null) {
    override fun processOrder(
        book: Book?,
        seller: UserInf,
        invoice: Invoice?,
        paymentStrategy: String?
    ) {
        Log.d("Online Library : AddInvoiceToSellerHandler", "Processing payment for order");
        addInvoiceToSeller(seller, invoice)
        nextHandler?.processOrder(book, seller, invoice, paymentStrategy)
    }

    private fun addInvoiceToSeller(seller: UserInf, invoice: Invoice?) {
        userNetwork = UserNetwork.getInstance()
        val invoiceList = seller.invoices.toMutableList()
        invoiceList.add(invoice!!.createNestedObject())
        val updatedUser = seller.copy(invoices = invoiceList)
        userNetwork?.updateUser(updatedUser)
    }
}