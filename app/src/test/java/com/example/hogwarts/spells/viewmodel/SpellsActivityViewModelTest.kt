package com.example.hogwarts.spells.viewmodel

import androidx.lifecycle.ViewModel
import com.example.hogwarts.spells.repo.SpellsRepo
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class SpellsActivityViewModelTest {
    private lateinit var viewModel: SpellsActivityViewModel
    @Mock
    private lateinit var mockSpellsRepo: SpellsRepo

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        viewModel = SpellsActivityViewModel(mockSpellsRepo)
    }

    @Test
    fun `test SpellsActivityViewModel is instance of ViewModel` () {
        assertTrue(ViewModel::class.java.isInstance(viewModel))
    }
}