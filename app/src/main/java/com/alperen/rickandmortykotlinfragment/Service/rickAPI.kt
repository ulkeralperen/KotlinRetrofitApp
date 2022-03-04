package com.alperen.rickandmortykotlinfragment.Service

import com.alperen.rickandmortykotlinfragment.Model.rickModel
import retrofit2.http.GET

interface rickAPI {

    @GET("character")
    fun getData():retrofit2.Call<rickModel>

}