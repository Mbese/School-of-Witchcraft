package com.example.hogwarts.service

import com.example.hogwarts.model.House
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GetHousesService {
    @GET("houses")
    suspend fun getHouses(@Query("key") key : String) : Response<List<House>>
}