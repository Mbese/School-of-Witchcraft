package com.example.hogwarts.houses.viewmodel

import androidx.lifecycle.ViewModel
import com.example.hogwarts.houses.repository.HouseRepo

class MainActivityViewModel (repo: HouseRepo) : ViewModel() {
    val houses = repo.houses
}
