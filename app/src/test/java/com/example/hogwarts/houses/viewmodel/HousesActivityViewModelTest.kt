package com.example.hogwarts.houses.viewmodel

import androidx.lifecycle.ViewModel
import com.example.hogwarts.houses.repository.HouseRepo
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class HousesActivityViewModelTest {
    @Mock
    private lateinit var mockHouseRepo: HouseRepo
    private lateinit var viewModel: HousesActivityViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        viewModel = HousesActivityViewModel(mockHouseRepo)
    }

    @Test
    fun `test HousesActivityViewModel is instance of ViewModel` () {
        assertTrue(ViewModel::class.java.isInstance(viewModel))
    }
}