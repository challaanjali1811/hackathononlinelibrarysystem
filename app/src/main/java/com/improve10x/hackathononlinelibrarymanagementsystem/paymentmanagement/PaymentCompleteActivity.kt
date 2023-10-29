package com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement

import android.content.Intent
import android.os.Bundle
import com.improve10x.hackathononlinelibrarymanagementsystem.BaseActivity
import com.improve10x.hackathononlinelibrarymanagementsystem.bookmanagement.BookActivity
import com.improve10x.hackathononlinelibrarymanagementsystem.databinding.ActivityPaymentCompleteBinding

class PaymentCompleteActivity : BaseActivity() {
    private var binding: ActivityPaymentCompleteBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentCompleteBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        supportActionBar?.title = "Payment"
        handleDone()
    }

    private fun handleDone() {
        binding?.doneBtn?.setOnClickListener {
            finish()
            val intent = Intent(this, BookActivity::class.java)
            intent.putExtra("paid", true)
            startActivity(intent)
        }
    }
}