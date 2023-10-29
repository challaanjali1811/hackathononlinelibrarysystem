package com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import com.improve10x.hackathononlinelibrarymanagementsystem.BaseActivity
import com.improve10x.hackathononlinelibrarymanagementsystem.bookmanagement.Book
import com.improve10x.hackathononlinelibrarymanagementsystem.databinding.ActivityPaymentBinding
import com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement.postpayment.AddBookToBuyerHandler
import com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement.postpayment.AddBookToSellerHandler
import com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement.postpayment.AddInvoiceToBuyerHandler
import com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement.postpayment.AddInvoiceToSellerHandler
import com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement.postpayment.CreateInvoiceHandler
import com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement.postpayment.OrderHandler
import com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement.postpayment.UpdateBookHandler
import com.improve10x.hackathononlinelibrarymanagementsystem.usermanagement.UserInf


class PaymentActivity : BaseActivity() {
    private var binding: ActivityPaymentBinding? = null
    companion object{
        var seller: UserInf? = null
    }
    private var book: Book? = null
    private var paymentStrategy : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        book = intent.getSerializableExtra("book") as? Book
        handleContinue()
        handleUpi()
    }

    private fun handleUpi() {
        binding?.upiTv?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding?.upiEd?.visibility = View.VISIBLE
            } else {
                binding?.upiEd?.visibility = View.GONE
            }
        }
    }

    private fun handleContinue() {
        binding?.continueBtn?.setOnClickListener {
            processPaymentAndAfterSteps()
        }
        binding?.continueBtn2?.setOnClickListener {
            processPaymentAndAfterSteps()
        }
    }

    private fun processPaymentAndAfterSteps() {
        buy()
        val processingChain: OrderHandler = UpdateBookHandler(
            AddBookToBuyerHandler(
                AddBookToSellerHandler(
                    CreateInvoiceHandler(
                        AddInvoiceToBuyerHandler(
                            AddInvoiceToSellerHandler(null)
                        )
                    )
                )
            )
        )
        processingChain.processOrder(book, seller!!, null, paymentStrategy)
        goNext()
    }

    private fun goNext() {
        finish()
        val intent = Intent(this, PaymentCompleteActivity::class.java)
        startActivity(intent)
    }

    private fun buy() {
        var paymentStrategy: String = if (binding?.CreditCardRb?.isChecked == true)
            "CreditCard"
        else
            (findViewById<RadioButton>(binding!!.radioGroup.checkedRadioButtonId)).text.toString()
        this.paymentStrategy = paymentStrategy
        val amount = book!!.price!!
        processPayment(paymentStrategy, java.lang.Double.parseDouble(amount))
    }

    private fun processPayment(paymentStrategy: String, amount: Double) {
        val processor = PaymentProcessor()
        processor.setPaymentStrategy(paymentStrategy)
        processor.processPayment(amount)
    }

    override fun onResume() {
        super.onResume()
        supportActionBar?.title = "Payment"
        binding?.upiEd?.visibility = View.GONE
    }
}
