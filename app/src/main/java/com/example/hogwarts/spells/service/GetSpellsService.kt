package com.example.hogwarts.spells.service

import com.example.hogwarts.spells.data.Spell
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GetSpellsService {
    @GET("spells")
    suspend fun getSpells(@Query("key") key : String) : Response<List<Spell>>
}