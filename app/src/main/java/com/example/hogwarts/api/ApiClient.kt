package com.example.hogwarts.api

import com.example.hogwarts.characters.service.GetCharactersService
import com.example.hogwarts.constants.BASE_URL
import com.example.hogwarts.houses.service.GetHousesService
import com.example.hogwarts.spells.service.GetSpellsService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        private fun getRetrofitInstance(): Retrofit.Builder {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
        }

        fun getHousesService(): GetHousesService {
            return getRetrofitInstance().build().create(GetHousesService::class.java)
        }

        fun getCharactersService(): GetCharactersService {
            return getRetrofitInstance().build().create(GetCharactersService::class.java)
        }

        fun getSpellsService(): GetSpellsService {
            return getRetrofitInstance().build().create(GetSpellsService::class.java)
        }
    }
}