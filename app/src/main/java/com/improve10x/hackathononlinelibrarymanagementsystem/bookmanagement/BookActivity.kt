package com.improve10x.hackathononlinelibrarymanagementsystem.bookmanagement

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.improve10x.hackathononlinelibrarymanagementsystem.BaseActivity
import com.improve10x.hackathononlinelibrarymanagementsystem.databinding.ActivityBookBinding
import com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement.PaymentActivity
import com.improve10x.hackathononlinelibrarymanagementsystem.usermanagement.AddUserActivity
import com.improve10x.hackathononlinelibrarymanagementsystem.usermanagement.UserMgr
import com.improve10x.hackathononlinelibrarymanagementsystem.usermanagement.UserMgr.Companion.getCurrentUser
import com.improve10x.hackathononlinelibrarymanagementsystem.usermanagement.UserNetwork

class BookActivity : BaseActivity() {
    private var binding: ActivityBookBinding? = null
    private var booksAdapter: BooksAdapter? = null
    private var bookList = mutableListOf<Book>()
    private val network = BookNetwork.getInstance()
    private val userNetwork = UserNetwork.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        setupBookListAdapter()
        setupBookListRv()
        setupFabClick();
        UserMgr.getInstance()
    }

    private fun setupFabClick() {
        binding?.addFab?.setOnClickListener {
            val intent = Intent(this, AddBookActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        supportActionBar?.title = "Books"
        getBookList()

    }

    private fun getBookList() {
        network.getBooks(object : OnGetBooksDataListener {
            override fun onBooksReceived(books: List<Book>) {
                bookList.clear()
                bookList.addAll(books)
                booksAdapter?.setData(bookList)
            }

            override fun onFailedToReceiveBooks(ex: Exception) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun setupBookListAdapter() {
        booksAdapter = BooksAdapter()
        booksAdapter!!.setData(bookList)
    }

    private fun setupBookListRv() {
        binding?.booksRv?.setLayoutManager(GridLayoutManager(this, 2))
        binding?.booksRv?.setAdapter(booksAdapter)
        booksAdapter?.setOnItemActionListener(object : OnItemActionListener{
            override fun onItemClicked(book: Book?) {
                val intent = Intent(this@BookActivity, BookDetailsActivity::class.java)
                intent.putExtra("book", book)
                startActivity(intent)
            }

            override fun onEditClicked(book: Book?) {
                val intent = Intent(this@BookActivity, AddBookActivity::class.java)
                intent.putExtra("book", book)
                startActivity(intent)
            }

            override fun onDeleteClicked(book: Book?) {
                if (book != null) {
                    network.deleteBook(book)
                    getBookList()
                };
            }

            override fun onBuyNowClicked(book: Book?) {
                if(Integer.parseInt(book!!.unitsLeft) >= 1){
                    userNetwork?.getUser(book!!.sellerId)
                    val intent = Intent(this@BookActivity, PaymentActivity::class.java)
                    intent.putExtra("book", book)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@BookActivity,
                        "Stock not available. Please try after sometime",
                        Toast.LENGTH_LONG)
                }
            }
        })
    }
}