package com.improve10x.hackathononlinelibrarymanagementsystem.bookmanagement

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import com.improve10x.hackathononlinelibrarymanagementsystem.BaseActivity
import com.improve10x.hackathononlinelibrarymanagementsystem.databinding.ActivityAddBookBinding

class AddBookActivity : BaseActivity() {
    private var binding: ActivityAddBookBinding? = null
    val network = BookNetwork.getInstance()
    private var book : Book? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBookBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        val intent = intent
        book = intent.getSerializableExtra("book") as? Book
        book?.let {
            populateFields(book!!)
        }
        setupTickFabClick()
        setupUploadClick()
    }

    private fun populateFields(book: Book) {
        supportActionBar?.title = "Edit Book"
         binding?.authorEd?.setText(book.author)
         binding?.genreEd?.setText(book.genre)
         binding?.priceEd?.setText(book.price)
         binding?.unitsLeftEd?.setText(book.unitsLeft)
         binding?.unitsSoldEd?.setText(book.unitsSold)
         binding?.imageUrlEd?.setText(book.url)
         binding?.publishedDateEd?.setText(book.publishedDate)
         binding?.qtyEd?.setText(book.qty)
         binding?.titleEd?.setText(book.title)
         binding?.versionEd?.setText(book.version)
    }

    private fun setupUploadClick() {
        binding?.uploadPdfBtn?.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "application/pdf"
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            startActivityForResult(Intent.createChooser(intent, "Select File"), 1000)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode != RESULT_CANCELED && requestCode == 1000) {
            data?.data?.let {
                uploadFile(it)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun uploadFile(fileUri: Uri) {
        val bookRef = Firebase.storage.reference
            .child("${
                book?.title ?: ("/books/" + binding?.titleEd?.text.toString() + binding?.versionEd?.text.toString())
            }.pdf")

        var uploadTask = bookRef.putFile(fileUri)
        uploadTask.addOnSuccessListener {
            Toast.makeText(this, "File Uploaded Successfully", Toast.LENGTH_SHORT).show()
            Log.d("Online Library : Books", "Uploading file of ${it.totalByteCount} bytes with filename ${it.uploadSessionUri.toString()}")
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to Upload File", Toast.LENGTH_SHORT).show()
        }

    }

    private fun setupTickFabClick() {
        binding?.tickFab?.setOnClickListener {
            if(book == null) {
                saveBook(book = createBook());
            } else {
                updateBook(book!!)
            }
        }
    }

    private fun updateBook(book: Book) {
        val updatedBook = createBook().copy(id = book.id)
        network.updateBook(updatedBook)
        finishAndMove()
    }

    private fun finishAndMove() {
        finish()
        val intent = Intent(this, BookActivity::class.java)
        startActivity(intent)
    }

    private fun createBook(): Book {
        return Book(
            author = binding?.authorEd?.text.toString(),
            genre = binding?.genreEd?.text.toString(),
            price = binding?.priceEd?.text.toString(),
            unitsLeft = binding?.unitsLeftEd?.text.toString(),
            unitsSold = binding?.unitsSoldEd?.text.toString(),
            url = binding?.imageUrlEd?.text.toString(),
            publishedDate = binding?.publishedDateEd?.text.toString(),
            qty = binding?.qtyEd?.text.toString(),
            title = binding?.titleEd?.text.toString(),
            version = binding?.versionEd?.text.toString())
    }

    private fun saveBook(book : Book) {
        network.addBook(book)
        finishAndMove()
    }

    override fun onResume() {
        super.onResume()
        if(book == null){
            supportActionBar?.title = "Add Book"
        }
    }

}