package com.nicole.publisher

import com.google.firebase.Timestamp

data class Article(
    var title: String? = null,
    var content: String? = null,
    var created_time: Long = System.currentTimeMillis(),
    var id: String? = null,
    var category: String? = null)


data class Author(
    var email : String = "wayne@school.appworks.tw",
    var id : String = "waynechen323",
    var name : String = "AKA小安老師")