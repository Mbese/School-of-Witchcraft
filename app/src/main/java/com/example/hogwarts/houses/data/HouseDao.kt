package com.example.hogwarts.houses.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HouseDao {
    @Query("SELECT * FROM houses ORDER BY _id DESC")
    fun getHouses() : LiveData<List<House>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(houses: List<House>)
}