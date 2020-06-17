package com.example.hogwarts.characters.viewmodel

import androidx.lifecycle.ViewModel
import com.example.hogwarts.characters.repo.CharacterRepo

class HouseCharactersViewModel (repo: CharacterRepo) : ViewModel() {
    val characters = repo.characters
}