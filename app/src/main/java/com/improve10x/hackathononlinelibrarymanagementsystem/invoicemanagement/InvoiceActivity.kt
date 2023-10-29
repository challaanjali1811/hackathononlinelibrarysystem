package com.improve10x.hackathononlinelibrarymanagementsystem.invoicemanagement

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.improve10x.hackathononlinelibrarymanagementsystem.BaseActivity
import com.improve10x.hackathononlinelibrarymanagementsystem.databinding.ActivityInvoiceBinding
import com.improve10x.hackathononlinelibrarymanagementsystem.usermanagement.UserMgr

class InvoiceActivity : BaseActivity() {
    private val network = InvoiceNetwork.getInstance();
    private var binding: ActivityInvoiceBinding? = null
    private var invoicesAdapter: InvoicesAdapter? = null
    private var invoiceList = mutableListOf<Invoice>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInvoiceBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        getCurrentUserInvoices()
        setupInvoiceListAdapter()
        setupInvoiceListRv()
    }

    private fun getCurrentUserInvoices() {
        val invoices = UserMgr.getCurrentUser()?.invoices
        invoiceList.clear()
        if (invoices != null) {
            invoiceList.addAll(invoices)
        }
        invoicesAdapter?.setData(invoiceList)
    }

    override fun onResume() {
        super.onResume()
        supportActionBar?.title = "Invoices"
        getCurrentUserInvoices()
//        network.getInvoices(object : OnGetInvoicesDataListener {
//            override fun onInvoicesReceived(invoices: List<Invoice>) {
//                invoiceList.clear()
//                invoiceList.addAll(invoices)
//                invoicesAdapter?.setData(invoiceList)
//            }
//
//            override fun onFailedToReceiveInvoices(ex: Exception) {
//
//            }
//        })
    }

    private fun setupInvoiceListAdapter() {
        invoicesAdapter = InvoicesAdapter()
        invoicesAdapter!!.setData(invoiceList)
        invoicesAdapter!!.setOnItemActionListener(OnItemActionListener {
            itemClicked(it)
        })
    }

    private fun setupInvoiceListRv() {
        binding?.invoicesRv?.layoutManager = LinearLayoutManager(this)
        binding?.invoicesRv?.adapter = invoicesAdapter
    }

    private fun itemClicked(invoice: Invoice) {
        val intent = Intent(this, InvoiceDetailsActivity::class.java)
        intent.putExtra("invoice", invoice)
        startActivity(intent)
    }

}