package com.improve10x.hackathononlinelibrarymanagementsystem.bookmanagement;

import java.io.Serializable;

 data class Book (
    var id : String? = "",
    var author : String? = "",
    var genre : String? = "",
    var price : String? = "",
    var url : String? = "",
    var publishedDate : String? = "",
    var qty : String? = "",
    var title : String? = "",
    var unitsLeft : String? = "",
    var unitsSold : String? = "",
    var version : String? = "",
    var filePath : String? = "",
    var sellerId : String? = ""
) : Serializable