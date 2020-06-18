package com.example.hogwarts.houses.repository

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.example.hogwarts.constants.API_KEY
import com.example.hogwarts.houses.data.House
import com.example.hogwarts.houses.data.HouseDao
import com.example.hogwarts.api.ApiClient
import com.example.hogwarts.houses.service.GetHousesService
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HouseRepo(
    private val dao: HouseDao,
    private val apiService: GetHousesService = ApiClient.getHousesService()
) {
    val houses = MutableLiveData<List<House>>()

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.d("", "$exception handled !")
    }

    init {
        CoroutineScope(Dispatchers.IO + handler).launch {
            kotlin.runCatching {
                getHouses()
            }
        }
    }

    @WorkerThread
    suspend fun getHouses() {
        var housesData = dao.getHouses().value
        if (housesData.isNullOrEmpty()) {
            housesData = apiService.getHouses(API_KEY).body()
            if (!housesData.isNullOrEmpty()) {
                houses.postValue(housesData)
                dao.insertAll(housesData)
            }
        } else {
            houses.postValue(housesData)
        }
    }

}