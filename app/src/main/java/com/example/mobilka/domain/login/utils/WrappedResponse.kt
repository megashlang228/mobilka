package com.example.mobilka.domain.login.utils

data class WrappedResponse<T> (
    var code: Int,
    var message : String,
    var status : Boolean,
    var errors : List<String>? = null,
    var data : T? = null
)