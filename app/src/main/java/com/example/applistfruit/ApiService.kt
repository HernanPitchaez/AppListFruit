package com.example.applistfruit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET
    suspend fun getAllFruit(@Url url: String): Response<MutableList<Fruit>>

}