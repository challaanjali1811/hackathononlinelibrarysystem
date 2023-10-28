package com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement.postpayment

import android.util.Log
import com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement.PaymentActivity
import com.improve10x.hackathononlinelibrarymanagementsystem.usermanagement.UserNetwork

public class AddInvoiceToSellerHandler(private val orderHandler: OrderHandler?) : OrderHandler(null) {
    override fun processOrder(order: String?) {
        Log.d("AddInvoiceToSellerHandler", "Processing payment for order: $order");
        addInvoiceToSeller()
        nextHandler?.processOrder(order)
    }

    private fun addInvoiceToSeller() {
        userNetwork = UserNetwork.getInstance()
        val invoiceList = PaymentActivity.seller!!.invoices.toMutableList()
        invoiceList.add(PaymentActivity.invoice!!)
        val updatedUser = PaymentActivity.seller!!.copy(invoices = invoiceList)
        userNetwork?.updateUser(updatedUser)
    }
}