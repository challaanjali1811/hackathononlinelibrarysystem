package com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement.postpayment

import android.util.Log
import com.improve10x.hackathononlinelibrarymanagementsystem.bookmanagement.Book
import com.improve10x.hackathononlinelibrarymanagementsystem.invoicemanagement.Invoice
import com.improve10x.hackathononlinelibrarymanagementsystem.usermanagement.UserInf
import com.improve10x.hackathononlinelibrarymanagementsystem.usermanagement.UserMgr
import com.improve10x.hackathononlinelibrarymanagementsystem.usermanagement.UserNetwork

public class AddInvoiceToBuyerHandler(private val orderHandler: OrderHandler) : OrderHandler(orderHandler) {
    override fun processOrder(
        book: Book?,
        seller: UserInf,
        invoice: Invoice?,
        paymentStrategy: String?
    ) {
        Log.d("Online Library : AddInvoiceToBuyerHandler", "Processing payment for order");
        addInvoiceToBuyer(invoice)
        nextHandler?.processOrder(book, seller, invoice, paymentStrategy)
    }

    private fun addInvoiceToBuyer(invoice: Invoice?){
        userNetwork = UserNetwork.getInstance()
        val buyer = UserMgr.getCurrentUser()!!
        val invoiceList = buyer.invoices.toMutableList()
        invoiceList.add(invoice!!.createNestedObject())
        val updatedUser = buyer.copy(invoices = invoiceList)
        UserMgr.setCurrentUser(updatedUser)
        userNetwork?.updateUser(updatedUser)
    }
}