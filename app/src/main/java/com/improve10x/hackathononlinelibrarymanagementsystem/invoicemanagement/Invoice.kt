package com.improve10x.hackathononlinelibrarymanagementsystem.invoicemanagement;

import com.improve10x.hackathononlinelibrarymanagementsystem.bookmanagement.Book
import com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement.PaymentInfo
import com.improve10x.hackathononlinelibrarymanagementsystem.usermanagement.UserInf
import java.io.Serializable;

 data class Invoice (
    var id : String? = "",
    var buyerDetails : UserInf? = null,
    var sellerDetails : UserInf? = null,
    var appName : String? = "RENUKA CHALLA",
    var purchaseDate : String? = "",
    var quantity : String? = "",
    var orderId : String? = "",
    var paymentInfo : PaymentInfo? = null,
    var book : Book? = null,
) : Serializable {
   fun createNestedObject() : Invoice {
      return this.copy(
         buyerDetails = buyerDetails?.copy(
            invoices = emptyList(),
            books = emptyList()
         ),
         sellerDetails = sellerDetails?.copy(
            invoices = emptyList(),
            books = emptyList()
         )
      )
   }
}

