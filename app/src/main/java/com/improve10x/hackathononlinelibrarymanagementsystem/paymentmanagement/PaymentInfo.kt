package com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement

data class PaymentInfo (
    var paymentMode : String? = "",
    var totalPrice : String? = "",
    var gstPrice : String? = "",
    var currency : String? = "",
)