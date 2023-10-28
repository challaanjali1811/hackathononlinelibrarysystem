package com.improve10x.hackathononlinelibrarymanagementsystem

class InvoiceBuilder {
    private var id: String? = null
    private var buyerDetails: String? = null
    private var sellerDetails: String? = null
    private var appName: String? = null
    private var purchaseDate: String? = null
    private var quantity: String? = null
    private var orderId: String? = null
    private var paymentInfo: String? = null
    private var book: String? = null
    fun setId(id: String?): InvoiceBuilder {
        this.id = id
        return this
    }

    fun setBuyerDetails(buyerDetails: String?): InvoiceBuilder {
        this.buyerDetails = buyerDetails
        return this
    }

    fun setSellerDetails(sellerDetails: String?): InvoiceBuilder {
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

    fun setPaymentInfo(paymentInfo: String?): InvoiceBuilder {
        this.paymentInfo = paymentInfo
        return this
    }

    fun setBook(book: String?): InvoiceBuilder {
        this.book = book
        return this
    }

    fun createInvoice(): Invoice {
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