package com.example.hogwarts.houses.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface HouseDao {
    @Query("SELECT * FROM houses ORDER BY _id DESC")
    fun getHouses() : LiveData<List<House>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(houses: List<House>)
}