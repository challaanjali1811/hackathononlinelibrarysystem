package com.improve10x.hackathononlinelibrarymanagementsystem.bookmanagement.search

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.improve10x.hackathononlinelibrarymanagementsystem.bookmanagement.Book
import com.improve10x.hackathononlinelibrarymanagementsystem.bookmanagement.OnGetBooksDataListener
import com.improve10x.hackathononlinelibrarymanagementsystem.usermanagement.UserMgr

class SearchBookNetwork private constructor() {
    companion object {
        var searchResult : List<Book>? = null
        @Volatile
        private var instance: SearchBookNetwork? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: SearchBookNetwork().also { instance = it }
            }
    }
    fun getAdvancedSearch(title : String?, author : String?, genre : String?,
                          onGetBooksDataListener: OnGetBooksDataListener) {
        val db = FirebaseFirestore.getInstance()
        db.collection("books")
            .get()
            .addOnSuccessListener { book ->
                val books = book.documents?.mapNotNull { it.toObject(Book::class.java) }
                if (books != null) {
                    Log.d("Search Books", Gson().toJson(books))
                    var searchList = emptyList<Book>()
                    searchList = searchByTitle(searchList, title, books)
                    searchList = searchByAuthor(searchList, author)
                    searchList = searchByGenre(searchList, genre)
                    onGetBooksDataListener.onBooksReceived(searchList)
                } else {
                    Log.d("Search Books", "Books is null");
                }
            }
            .addOnFailureListener {
                Log.e(this.javaClass.simpleName, it.message, it)
                onGetBooksDataListener.onFailedToReceiveBooks(it)
            }
    }

    fun getSearch(searchQuery : String?, onGetBooksDataListener: OnGetBooksDataListener) {
        val db = FirebaseFirestore.getInstance()
        db.collection("books")
            .get()
            .addOnSuccessListener { book ->
                val books = book.documents?.mapNotNull { it.toObject(Book::class.java) }
                if (books != null) {
                    Log.d("Search Books", Gson().toJson(books))
                    val searchList = searchByQuery(searchQuery, books)
                    onGetBooksDataListener.onBooksReceived(searchList)
                } else {
                    Log.d("Search Books", "Books is null");
                }
            }
            .addOnFailureListener {
                Log.e(this.javaClass.simpleName, it.message, it)
                onGetBooksDataListener.onFailedToReceiveBooks(it)
            }
    }

    private fun searchByQuery(searchQuery: String?, books: List<Book>): List<Book> {
        var searchList = books
        searchList = if(!searchQuery.isNullOrBlank()) {
            searchList.filter {
                it.title?.contains(searchQuery, true) ?: false ||
                        it.author?.contains(searchQuery, true) ?: false ||
                        it.genre?.contains(searchQuery, true) ?: false
            }.toList()
        } else {
            books
        }
        return searchList
    }

    private fun searchByGenre(
        searchList: List<Book>,
        genre: String?
    ): List<Book> {
        var searchList1 = searchList
        searchList1 = if (!genre.isNullOrBlank()) {
            searchList1.filter {
                it.genre?.contains(genre, true) ?: false
            }.toList()
        } else {
            searchList1;
        }
        return searchList1
    }

    private fun searchByAuthor(
        searchList: List<Book>,
        author: String?
    ): List<Book> {
        var searchList1 = searchList
        searchList1 = if (!author.isNullOrBlank()) {
            searchList1.filter {
                it.author?.contains(author, true) ?: false
            }.toList()
        } else {
            searchList1;
        }
        return searchList1
    }

    private fun searchByTitle(
        searchList: List<Book>,
        title: String?,
        books: List<Book>
    ): List<Book> {
        var searchList1 = searchList
        searchList1 = if (!title.isNullOrBlank()) {
            books.filter {
                it.title?.contains(title, true) ?: false
            }.toList()
        } else {
            books;
        }
        return searchList1
    }
//
//    fun addBook(book: Book) {
//        val db = FirebaseFirestore.getInstance()
//        book.id = db.collection("books").document().id
//        book.filePath = "/books/"+ book.id+".pdf"
//        book.sellerId = UserMgr.getCurrentUser()!!.id
//        db.collection("books")
//            .document(book.id!!)
//            .set(book)
//            .addOnSuccessListener {
//                Log.d("Books", "Book added into list")
//            }
//            .addOnFailureListener {
//                Log.e(this.javaClass.simpleName, it.message, it)
//            }
//    }
//
//    fun updateBook(book: Book) {
//        val db = FirebaseFirestore.getInstance()
//        db.collection("books")
//            .document(book.id ?: "")
//            .set(book)
//            .addOnSuccessListener {
//                Log.d("Books", "Book details updated")
//            }.addOnFailureListener {
//                Log.e(this.javaClass.simpleName, it.message, it)
//            }
//    }
//
//    fun deleteBook(book: Book) {
//        val db = FirebaseFirestore.getInstance()
//        db.collection("books")
//            .document(book.id!!)
//            .delete()
//            .addOnSuccessListener {
//                Log.d("Books", "Book deleted from list")
//            }
//            .addOnFailureListener {
//                Log.e(this.javaClass.simpleName, it.message, it)
//            }
//    }
}