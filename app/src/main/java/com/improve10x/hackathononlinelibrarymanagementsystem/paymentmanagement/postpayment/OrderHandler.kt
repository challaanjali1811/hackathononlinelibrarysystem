package com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement.postpayment

import com.improve10x.hackathononlinelibrarymanagementsystem.bookmanagement.Book
import com.improve10x.hackathononlinelibrarymanagementsystem.bookmanagement.BookNetwork
import com.improve10x.hackathononlinelibrarymanagementsystem.invoicemanagement.Invoice
import com.improve10x.hackathononlinelibrarymanagementsystem.invoicemanagement.InvoiceNetwork
import com.improve10x.hackathononlinelibrarymanagementsystem.usermanagement.UserInf
import com.improve10x.hackathononlinelibrarymanagementsystem.usermanagement.UserMgr
import com.improve10x.hackathononlinelibrarymanagementsystem.usermanagement.UserNetwork

abstract class OrderHandler(protected var nextHandler: OrderHandler?, ) {
    protected var bookNetwork : BookNetwork? = BookNetwork.getInstance()
    protected var userNetwork : UserNetwork? = UserNetwork.getInstance()
    protected var invoiceNetwork : InvoiceNetwork? = InvoiceNetwork.getInstance()
    protected var user : UserInf? = UserMgr.getCurrentUser()
    abstract fun processOrder(book: Book?, seller: UserInf, invoice: Invoice?, paymentStrategy: String?)
}