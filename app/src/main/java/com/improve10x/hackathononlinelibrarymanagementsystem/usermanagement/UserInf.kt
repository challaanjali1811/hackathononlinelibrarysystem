package com.improve10x.hackathononlinelibrarymanagementsystem.usermanagement

import com.improve10x.hackathononlinelibrarymanagementsystem.bookmanagement.Book
import com.improve10x.hackathononlinelibrarymanagementsystem.invoicemanagement.Invoice
import java.io.Serializable

data class UserInf (
    var id : String? = "",
    var role : String? = "",
    var name : String? = "",
    var address : String? = "",
    var mobile : String? = "",
    var email : String? = "",
    var PAN : String? = "",
    var GSTNo : String? = "",
    var registeredDate : String? = "",
    var isActive : Boolean = true,
    var invoices : List<Invoice> = emptyList(),
    var books : List<Book> = emptyList()
) : Serializable {
    fun createNestedObject() : UserInf {
        return this.copy(
            invoices = emptyList(),
            books = emptyList()
        )
    }
}
