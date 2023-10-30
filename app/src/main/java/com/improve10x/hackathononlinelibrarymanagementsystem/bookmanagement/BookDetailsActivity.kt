package com.improve10x.hackathononlinelibrarymanagementsystem.bookmanagement

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import com.google.firebase.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage
import com.improve10x.hackathononlinelibrarymanagementsystem.BaseActivity
import com.improve10x.hackathononlinelibrarymanagementsystem.databinding.BookDetailsBinding
import com.improve10x.hackathononlinelibrarymanagementsystem.usermanagement.UserMgr
import com.squareup.picasso.Picasso
import java.io.File

class BookDetailsActivity : BaseActivity() {
    private var book: Book? = null
    private var role: String? = null
    private var binding: BookDetailsBinding? = null
    lateinit var storage: FirebaseStorage
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
        binding?.downloadBtn?.setOnClickListener {
            handleDownloadBtn()
        }
    }

    private fun handleDownloadBtn() {
            val downloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/hackathon-online-library-mgt.appspot.com/o/LLD_HACKATHON%20(1).pdf?alt=media&token=76ddefb5-93dc-4001-a762-fed8b15fa51d&_gl=1*4mxuet*_ga*MTY3Mjk3Mjg5Mi4xNjk4NDEwMzE0*_ga_CW55HF8NVT*MTY5ODYwMDc1My4yMC4xLjE2OTg2MDU2MTguNDAuMC4w")
            val request = DownloadManager.Request(uri)
            Log.d("Online Library : Downloaded Book", uri.toString() + "")
            request.setDescription(book?.title)
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, book?.title + ".pdf")
            downloadManager.enqueue(request)
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