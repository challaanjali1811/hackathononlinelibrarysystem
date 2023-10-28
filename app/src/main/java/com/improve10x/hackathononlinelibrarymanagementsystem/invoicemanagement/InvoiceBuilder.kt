package com.improve10x.hackathononlinelibrarymanagementsystem.invoicemanagement

import com.improve10x.hackathononlinelibrarymanagementsystem.bookmanagement.Book
import com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement.PaymentInfo
import com.improve10x.hackathononlinelibrarymanagementsystem.usermanagement.UserInf

class InvoiceBuilder {
   private var id: String? = null
   private var buyerDetails: UserInf? = null
   private var sellerDetails: UserInf? = null
   private var appName: String? = null
   private var purchaseDate: String? = null
   private var quantity: String? = null
   private var orderId: String? = null
   private var paymentInfo: PaymentInfo? = null
   private var book: Book? = null
   fun setId(id: String?): InvoiceBuilder {
      this.id = id
      return this
   }

   fun setBuyerDetails(buyerDetails: UserInf?): InvoiceBuilder {
      this.buyerDetails = buyerDetails
      return this
   }

   fun setSellerDetails(sellerDetails: UserInf?): InvoiceBuilder {
      this.sellerDetails = sellerDetails
      return this
   }

   fun setAppName(appName: String?): InvoiceBuilder {
      this.appName = appName
      return this
   }

   fun setPurchaseDate(purchaseDate: String?): InvoiceBuilder {
      this.purchaseDate = purchaseDate
      return this
   }

   fun setQuantity(quantity: String?): InvoiceBuilder {
      this.quantity = quantity
      return this
   }

   fun setOrderId(orderId: String?): InvoiceBuilder {
      this.orderId = orderId
      return this
   }

   fun setPaymentInfo(paymentInfo: PaymentInfo?): InvoiceBuilder {
      this.paymentInfo = paymentInfo
      return this
   }

   fun setBook(book: Book?): InvoiceBuilder {
      this.book = book
      return this
   }

   fun build(): Invoice {
      return Invoice(
          id,
          buyerDetails,
          sellerDetails,
          appName,
          purchaseDate,
          quantity,
          orderId,
          paymentInfo,
          book
      )
   }
}