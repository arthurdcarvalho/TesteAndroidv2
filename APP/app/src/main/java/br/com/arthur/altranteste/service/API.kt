package br.com.arthur.altranteste.service

import br.com.arthur.altranteste.model.Statements
import br.com.arthur.altranteste.model.User
import br.com.arthur.altranteste.service.request.LoginRequest
import retrofit2.Call
import retrofit2.http.*

interface API {

    @POST("login")
    fun login(@Body loginRequest: LoginRequest): Call<User>

    @FormUrlEncoded
    @GET("statement/{id}")
    fun requestInfo(@Path("id") id: Int): Call<Statements>

}