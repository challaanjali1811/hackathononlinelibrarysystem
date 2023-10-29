package com.improve10x.hackathononlinelibrarymanagementsystem.bookmanagement

import android.os.Bundle
import android.view.View
import com.improve10x.hackathononlinelibrarymanagementsystem.BaseActivity
import com.improve10x.hackathononlinelibrarymanagementsystem.databinding.BookDetailsBinding
import com.improve10x.hackathononlinelibrarymanagementsystem.usermanagement.UserMgr
import com.squareup.picasso.Picasso

class BookDetailsActivity : BaseActivity() {
    private var book: Book? = null
    private var role: String? = null
    private var binding: BookDetailsBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BookDetailsBinding.inflate(
            layoutInflater
        )
        setContentView(binding!!.root)
        supportActionBar!!.title = "Book Details"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        book = intent.getSerializableExtra("book") as Book?
        role = intent.getStringExtra("role")
        handleVisibility()
        showData()
    }

    private fun handleVisibility() {
        when (role) {
            "Buyer" -> {
                binding!!.unitsSoldTxt.visibility = View.GONE
                binding!!.totalUnitsTxt.visibility = View.GONE
                binding!!.unitsLeftTxt.visibility = View.GONE
                if (UserMgr.getCurrentUser()?.books?.filter { it.id == book?.id }
                        ?.isEmpty() == true) {
                    binding!!.downloadBtn.visibility = View.INVISIBLE
                } else {
                    binding!!.downloadBtn.visibility = View.VISIBLE
                }
            }
            else -> {
                binding!!.unitsSoldTxt.visibility = View.VISIBLE
                binding!!.totalUnitsTxt.visibility = View.VISIBLE
                binding!!.unitsLeftTxt.visibility = View.VISIBLE
            }
        }
    }

    private fun showData() {
        binding!!.titleTxt.text = "Title : " + book!!.title
        binding!!.authorTxt.text = "Author : " + book!!.author
        binding!!.genreTxt.text = "Genre : " + book!!.genre
        binding!!.priceTxt.text = "Price : Rs. " + book!!.price
        binding!!.publishedDateTxt.text = "Published Date : " + book!!.publishedDate
        binding!!.totalUnitsTxt.text = "Total Units : " + book!!.qty
        binding!!.unitsLeftTxt.text = "Units Remaining : " + book!!.unitsLeft
        binding!!.unitsSoldTxt.text = "Units Sold : " + book!!.unitsSold
        binding!!.versionTxt.text = "Version : " + book!!.version
        Picasso.get().load(book!!.url).into(binding!!.bookIv)
    }
}