package com.improve10x.hackathononlinelibrarymanagementsystem.bookmanagement

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.improve10x.hackathononlinelibrarymanagementsystem.usermanagement.UserMgr

class BookNetwork private constructor() {
    companion object {

        @Volatile
        private var instance: BookNetwork? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: BookNetwork().also { instance = it }
            }
    }
    fun getBooks(onGetBooksDataListener: OnGetBooksDataListener) {
        val db = FirebaseFirestore.getInstance()
        db.collection("books")
            .get()
            .addOnSuccessListener {
                val books = it.documents?.mapNotNull { it.toObject(Book::class.java) }
                if (books != null) {
                    onGetBooksDataListener.onBooksReceived(books)
                    Log.d("Online Library : Books", Gson().toJson(books))
                } else {
                    Log.d("Online Library : Books", "Books is null");
                }
            }
            .addOnFailureListener {
                Log.e("Online Library : " + this.javaClass.simpleName, it.message, it)
                onGetBooksDataListener.onFailedToReceiveBooks(it)
            }
    }

    fun addBook(book: Book) {
        val db = FirebaseFirestore.getInstance()
        book.id = db.collection("books").document().id
        book.filePath = "/books/"+ book.id+".pdf"
        book.sellerId = UserMgr.getCurrentUser()!!.id
        db.collection("books")
            .document(book.id!!)
            .set(book)
            .addOnSuccessListener {
                Log.d("Online Library : Books", "Book added into list")
            }
            .addOnFailureListener {
                Log.e("Online Library : " + this.javaClass.simpleName, it.message, it)
            }
    }

    fun updateBook(book: Book) {
        val db = FirebaseFirestore.getInstance()
        db.collection("books")
            .document(book.id ?: "")
            .set(book)
            .addOnSuccessListener {
                Log.d("Online Library : Books", "Book details updated")
            }.addOnFailureListener {
                Log.e("Online Library : " + this.javaClass.simpleName, it.message, it)
            }
    }

    fun deleteBook(book: Book) {
        val db = FirebaseFirestore.getInstance()
        db.collection("books")
            .document(book.id!!)
            .delete()
            .addOnSuccessListener {
                Log.d("Online Library : Books", "Book deleted from list")
            }
            .addOnFailureListener {
                Log.e("Online Library : " + this.javaClass.simpleName, it.message, it)
            }
    }
}

interface OnGetBooksDataListener {
    fun onBooksReceived(books: List<Book>)

    fun onFailedToReceiveBooks(ex: Exception)
}
