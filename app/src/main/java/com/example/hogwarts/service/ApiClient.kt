package com.example.hogwarts.service

import com.example.hogwarts.constants.Constants.Companion.base_url
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        fun getRetrofitInstance(): Retrofit.Builder {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(base_url)
        }

        fun getHousesService(): GetHousesService {
            return getRetrofitInstance().build().create(GetHousesService::class.java)
        }
    }
}