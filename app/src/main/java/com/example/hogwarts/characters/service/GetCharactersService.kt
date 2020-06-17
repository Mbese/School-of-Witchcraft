package com.example.hogwarts.characters.service

import com.example.hogwarts.characters.data.Character
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GetCharactersService {
    @GET("characters")
    suspend fun getCharacters(@Query("key") key : String) : Response<List<Character>>
}