package com.improve10x.hackathononlinelibrarymanagementsystem.paymentmanagement

import java.io.Serializable

data class PaymentInfo (
    var paymentMode : String? = "",
    var totalPrice : String? = "",
    var gstPrice : String? = "",
    var currency : String? = "",
) : Serializable