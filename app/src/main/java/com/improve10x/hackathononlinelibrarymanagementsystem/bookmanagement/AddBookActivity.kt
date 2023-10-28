package com.improve10x.hackathononlinelibrarymanagementsystem.bookmanagement

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
            Log.d("Books", "Upload clicked!")
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
