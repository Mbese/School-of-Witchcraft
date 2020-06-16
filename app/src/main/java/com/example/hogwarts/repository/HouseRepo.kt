package com.example.hogwarts.repository

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.example.hogwarts.constants.Constants
import com.example.hogwarts.model.House
import com.example.hogwarts.service.ApiClient
import com.example.hogwarts.service.GetHousesService
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HouseRepo (
    private val application: Application,
    private val apiService: GetHousesService = ApiClient.getHousesService()
) {
    val houses = MutableLiveData<List<House>>()

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.d("", "$exception handled !")
    }

    init {
        CoroutineScope(Dispatchers.IO + handler).launch {
            getHouses()
        }
    }

    @WorkerThread
    suspend fun getHouses() {
        if (networkAvailable()) {
            val housesData = apiService.getHouses(Constants.api_key).body()
            houses.postValue(housesData)
        }
    }

    @Suppress("DEPRECATION")
    private fun networkAvailable(): Boolean {
        val connectivityManager =
            application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnectedOrConnecting ?: false
    }
}