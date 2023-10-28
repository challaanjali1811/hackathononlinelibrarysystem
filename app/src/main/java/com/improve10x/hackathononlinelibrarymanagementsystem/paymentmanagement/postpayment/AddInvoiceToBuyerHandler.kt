package com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement.postpayment

import android.util.Log
import com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement.PaymentActivity
import com.improve10x.hackathononlinelibrarymanagementsystem.usermanagement.UserNetwork

public class AddInvoiceToBuyerHandler(private val orderHandler: OrderHandler) : OrderHandler(orderHandler) {
    override fun processOrder(order: String?) {
        Log.d("AddInvoiceToBuyerHandler", "Processing payment for order: $order");
        addInvoiceToBuyer()
        nextHandler?.processOrder(order)
    }

    private fun addInvoiceToBuyer() {
        userNetwork = UserNetwork.getInstance()
        val invoiceList = PaymentActivity.user!!.invoices.toMutableList()
        invoiceList.add(PaymentActivity.invoice!!)
        val updatedUser = PaymentActivity.user!!.copy(invoices = invoiceList)
        userNetwork?.updateUser(updatedUser)
    }
}