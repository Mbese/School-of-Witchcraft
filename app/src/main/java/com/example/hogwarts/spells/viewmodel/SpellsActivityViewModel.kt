package com.example.hogwarts.spells.viewmodel

import androidx.lifecycle.ViewModel
import com.example.hogwarts.spells.repo.SpellsRepo

class SpellsActivityViewModel (repo: SpellsRepo) : ViewModel() {
    val spells = repo.spells
}