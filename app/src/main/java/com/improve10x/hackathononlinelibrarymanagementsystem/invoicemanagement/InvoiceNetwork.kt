package com.improve10x.hackathononlinelibrarymanagementsystem.invoicemanagement

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson

class InvoiceNetwork private constructor() {
    companion object {

        @Volatile
        private var instance: InvoiceNetwork? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: InvoiceNetwork().also { instance = it }
            }
    }
    fun getInvoices(onGetInvoicesDataListener: OnGetInvoicesDataListener) {
        val db = FirebaseFirestore.getInstance()
        db.collection("invoices")
            .get()
            .addOnSuccessListener {
                val invoices = it.documents?.mapNotNull { it.toObject(Invoice::class.java) }
                if (invoices != null) {
                    onGetInvoicesDataListener.onInvoicesReceived(invoices)
                    Log.d("Invoices", Gson().toJson(invoices))
                } else {
                    Log.d("Invoices", "Invoices is null");
                }
            }
            .addOnFailureListener {
                Log.e(this.javaClass.simpleName, it.message, it)
                onGetInvoicesDataListener.onFailedToReceiveInvoices(it)
            }
    }

    fun addInvoice(invoice: Invoice) {
        val db = FirebaseFirestore.getInstance()
        invoice.id = db.collection("invoices").document().id
        invoice.orderId = "000" + invoice.id
        db.collection("invoices")
            .document(invoice.id!!)
            .set(invoice)
            .addOnSuccessListener {
                Log.d("Invoices", "Invoice added into list")
            }
            .addOnFailureListener {
                Log.e(this.javaClass.simpleName, it.message, it)
            }
    }

    fun updateInvoice(invoice: Invoice) {
        val db = FirebaseFirestore.getInstance()
        db.collection("invoices")
            .document(invoice.id ?: "")
            .set(invoice)
            .addOnSuccessListener {
                Log.d("Invoices", "Invoice details updated")
            }.addOnFailureListener {
                Log.e(this.javaClass.simpleName, it.message, it)
            }
    }

    fun deleteInvoice(invoice: Invoice) {
        val db = FirebaseFirestore.getInstance()
        db.collection("invoices")
            .document(invoice.id!!)
            .delete()
            .addOnSuccessListener {
                Log.d("Invoices", "Invoice deleted from list")
            }
            .addOnFailureListener {
                Log.e(this.javaClass.simpleName, it.message, it)
            }
    }
}

interface OnGetInvoicesDataListener {
    fun onInvoicesReceived(invoices: List<Invoice>)

    fun onFailedToReceiveInvoices(ex: Exception)
}
