package com.example.hogwarts.api

import com.example.hogwarts.constants.BASE_URL
import com.example.hogwarts.houses.service.GetHousesService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        fun getRetrofitInstance(): Retrofit.Builder {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
        }

        fun getHousesService(): GetHousesService {
            return getRetrofitInstance().build().create(GetHousesService::class.java)
        }
    }
}