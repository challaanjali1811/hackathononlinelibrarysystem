package com.improve10x.hackathononlinelibrarymanagementsystem.bookmanagement

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.improve10x.hackathononlinelibrarymanagementsystem.BaseActivity
import com.improve10x.hackathononlinelibrarymanagementsystem.R
import com.improve10x.hackathononlinelibrarymanagementsystem.bookmanagement.search.SearchBookNetwork
import com.improve10x.hackathononlinelibrarymanagementsystem.databinding.ActivityBookBinding
import com.improve10x.hackathononlinelibrarymanagementsystem.invoicemanagement.InvoiceActivity
import com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement.PaymentActivity
import com.improve10x.hackathononlinelibrarymanagementsystem.usermanagement.UserMgr
import com.improve10x.hackathononlinelibrarymanagementsystem.usermanagement.UserNetwork

class BookActivity : BaseActivity() {
    private var binding: ActivityBookBinding? = null
    private var booksAdapter: BooksAdapter? = null
    private var bookList = mutableListOf<Book>()
    private val network = BookNetwork.getInstance()
    private val searchNetwork = SearchBookNetwork.getInstance()
    private val userNetwork = UserNetwork.getInstance()
    private var role: String? = null
    private var isPaid: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        isPaid = intent.getBooleanExtra("paid", false)
        setupRole()
        setupBookListAdapter()
        setupBookListRv()
        setupFabClick();
        handleAdvancedSearchCb()
        handleSearchBtn()
    }

    private fun setupRole() {
        this.role = UserMgr.getCurrentUser()?.role
        this.role?.let { Log.d("Online Library : Current user ", it) }
    }

    private fun handleSearchBtn() {
        binding?.searchBtn?.setOnClickListener {
            if (binding?.asCb?.isChecked == true) {
                advancedSearch()
            } else {
                search()
            }
        }
    }

    private fun search() {
        val searchText = binding?.searchView?.query.toString()
        searchNetwork.getSearch(searchText, object : OnGetBooksDataListener {
            override fun onBooksReceived(books: List<Book>) {
                bookList.clear()
                bookList.addAll(books)
                booksAdapter?.setData(bookList)
            }

            override fun onFailedToReceiveBooks(ex: Exception) {

            }
        })
    }

    private fun advancedSearch() {
        val title = binding?.searchTitleEd?.text.toString()
        val author = binding?.searchAuthorEd?.text.toString()
        val genre = binding?.searchGenreEd?.text.toString()
        searchNetwork.getAdvancedSearch(title, author, genre, object : OnGetBooksDataListener {
            override fun onBooksReceived(books: List<Book>) {
                bookList.clear()
                bookList.addAll(books)
                booksAdapter?.setData(bookList)
            }

            override fun onFailedToReceiveBooks(ex: Exception) {
                Log.e("Online Library : " + this.javaClass.simpleName, ex.message, ex)
            }
        })
    }

    private fun handleAdvancedSearchCb() {
        binding?.asCb?.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                handleAdvancedSearchVisibility()
            } else {
                handleVisibility()
            }
        }
    }

    private fun handleAdvancedSearchVisibility() {
        binding?.searchView?.visibility = View.GONE
        binding?.searchGenreEd?.visibility = View.VISIBLE
        binding?.searchAuthorEd?.visibility = View.VISIBLE
        binding?.searchTitleEd?.visibility = View.VISIBLE
    }

    private fun handleVisibility() {
        binding?.searchView?.visibility = View.VISIBLE
        binding?.searchGenreEd?.visibility = View.GONE
        binding?.searchAuthorEd?.visibility = View.GONE
        binding?.searchTitleEd?.visibility = View.GONE
        when (role) {
            "Buyer" -> {
                binding?.addFab?.visibility = View.GONE
            }
        }
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
        handleVisibility()
        if (isPaid) {
            getPurchasedBookList()
        } else {
            getBookList()
        }
    }

    private fun getPurchasedBookList() {
        bookList.clear()
        UserMgr.getCurrentUser()?.books?.let { bookList.addAll(it) }
        booksAdapter?.setData(bookList)
    }

    private fun getBookList() {
        network.getBooks(object : OnGetBooksDataListener {
            override fun onBooksReceived(books: List<Book>) {
                bookList.clear()
                bookList.addAll(books)
                booksAdapter?.setData(bookList)
            }

            override fun onFailedToReceiveBooks(ex: Exception) {
                Log.e("Online Library : " + this.javaClass.simpleName, ex.message, ex)
            }
        })
    }

    private fun setupBookListAdapter() {
        booksAdapter = BooksAdapter(this.role)
        booksAdapter!!.setData(bookList)
    }

    private fun setupBookListRv() {
        binding?.booksRv?.setLayoutManager(GridLayoutManager(this, 2))
        binding?.booksRv?.setAdapter(booksAdapter)
        booksAdapter?.setOnItemActionListener(object : OnItemActionListener {
            override fun onItemClicked(book: Book?) {
                val intent = Intent(this@BookActivity, BookDetailsActivity::class.java)
                intent.putExtra("book", book)
                intent.putExtra("role", role)
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
                if (Integer.parseInt(book!!.unitsLeft) >= 1) {
                    userNetwork?.getUser(book!!.sellerId)
                    val intent = Intent(this@BookActivity, PaymentActivity::class.java)
                    intent.putExtra("book", book)
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        this@BookActivity,
                        "Stock not available. Please try after sometime",
                        Toast.LENGTH_LONG
                    )
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.sales) {
            val intent = Intent(this@BookActivity, InvoiceActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            super.onOptionsItemSelected(item)
        }
        return true
    }
}