package com.example.hogwarts.characters.viewmodel

import androidx.lifecycle.ViewModel
import com.example.hogwarts.characters.repo.CharacterRepo
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class HouseCharactersViewModelTest {

    private lateinit var viewModel: HouseCharactersViewModel
    @Mock private lateinit var mockCharactersRepo: CharacterRepo

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        viewModel = HouseCharactersViewModel(mockCharactersRepo)
    }

    @Test
    fun `test HouseCharactersViewModel is instance of ViewModel` () {
        assertTrue(ViewModel::class.java.isInstance(viewModel))
    }

}