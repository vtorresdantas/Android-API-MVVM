package com.example.matheus.model

import retrofit2.Call
import retrofit2.http.GET

interface IQuote {
    @GET("quote")
    fun getKanyeText(): Call<Frase>
}
