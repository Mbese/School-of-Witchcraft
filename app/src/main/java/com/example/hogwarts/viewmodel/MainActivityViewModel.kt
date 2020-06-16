package com.example.hogwarts.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.hogwarts.model.House
import com.example.hogwarts.repository.HouseRepo

class MainActivityViewModel (application: Application) : AndroidViewModel(application) {
    private val repo: HouseRepo = HouseRepo(application)
    val houses: MutableLiveData<List<House>>?

    init {
        houses = repo.houses
    }
}