package br.com.arthur.altranteste.service.request

import com.google.gson.annotations.SerializedName

class LoginRequest(

    @SerializedName("user")
    val user: String,

    @SerializedName("password")
    val password: String

)
