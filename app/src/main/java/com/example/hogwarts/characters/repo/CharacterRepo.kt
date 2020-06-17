package com.example.hogwarts.characters.repo

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.example.hogwarts.api.ApiClient
import com.example.hogwarts.characters.data.Character
import com.example.hogwarts.characters.data.CharacterDao
import com.example.hogwarts.characters.service.GetCharactersService
import com.example.hogwarts.constants.API_KEY
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterRepo (
    private val characterDao: CharacterDao,
    private val apiService: GetCharactersService = ApiClient.getCharactersService()
) {

    val characters = MutableLiveData<List<Character>>()

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.d("", "$exception handled !")
    }

    init {
        CoroutineScope(Dispatchers.IO + handler).launch {
            getCharacters()
        }
    }

    @WorkerThread
    suspend fun getCharacters() {
        var housesData = characterDao.getCharacters().value
        if (housesData.isNullOrEmpty()) {
            housesData = apiService.getCharacters(API_KEY).body()
            if (!housesData.isNullOrEmpty()) {
                characters.postValue(housesData)
                characterDao.insertAll(housesData)
            }
        } else {
            Log.e("Repo", "Fetching from database")
            characters.postValue(housesData)
        }
    }
}