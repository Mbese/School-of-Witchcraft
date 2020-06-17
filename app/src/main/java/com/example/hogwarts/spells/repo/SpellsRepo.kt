package com.example.hogwarts.spells.repo

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.example.hogwarts.api.ApiClient
import com.example.hogwarts.constants.API_KEY
import com.example.hogwarts.spells.data.Spell
import com.example.hogwarts.spells.data.SpellDao
import com.example.hogwarts.spells.service.GetSpellsService
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SpellsRepo (
    private val dao: SpellDao,
    private val apiService: GetSpellsService = ApiClient.getSpellsService()
) {

    val spells = MutableLiveData<List<Spell>>()

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.d("", "$exception handled !")
    }

    init {
        CoroutineScope(Dispatchers.IO + handler).launch {
            getSpells()
        }
    }

    @WorkerThread
    suspend fun getSpells() {
        var housesData = dao.getSpells().value
        if (housesData.isNullOrEmpty()) {
            housesData = apiService.getSpells(API_KEY).body()
            if (!housesData.isNullOrEmpty()) {
                spells.postValue(housesData)
                dao.insertAll(housesData)
            }
        } else {
            Log.e("Repo", "Fetching from database")
            spells.postValue(housesData)
        }
    }
}