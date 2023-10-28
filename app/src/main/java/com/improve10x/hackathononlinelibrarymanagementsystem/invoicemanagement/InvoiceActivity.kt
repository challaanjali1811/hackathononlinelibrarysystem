package com.improve10x.hackathononlinelibrarymanagementsystem.invoicemanagement

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.improve10x.hackathononlinelibrarymanagementsystem.BaseActivity
import com.improve10x.hackathononlinelibrarymanagementsystem.databinding.ActivityInvoiceBinding

class InvoiceActivity : BaseActivity() {
    private val network = InvoiceNetwork.getInstance();
    private var binding: ActivityInvoiceBinding? = null
    private var invoicesAdapter: InvoicesAdapter? = null
    private var invoiceList = mutableListOf<Invoice>();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInvoiceBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        setupInvoiceListAdapter()
        setupInvoiceListRv()
    }

    override fun onResume() {
        super.onResume()
        supportActionBar?.title = "Invoices"
        network.getInvoices(object : OnGetInvoicesDataListener {
            override fun onInvoicesReceived(invoices: List<Invoice>) {
                invoiceList.clear()
                invoiceList.addAll(invoices)
                invoicesAdapter?.setData(invoiceList)
            }
            override fun onFailedToReceiveInvoices(ex: Exception) {
                TODO("Not yet implemented")
            }
        })

    }

    private fun setupInvoiceListAdapter() {
        invoicesAdapter =
            InvoicesAdapter()
        invoicesAdapter!!.setData(invoiceList)
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