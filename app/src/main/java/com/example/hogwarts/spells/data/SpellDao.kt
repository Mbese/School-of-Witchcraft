package com.example.hogwarts.spells.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SpellDao {
    @Query("SELECT * FROM spells ORDER BY _id DESC")
    fun getSpells() : LiveData<List<Spell>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(spells: List<Spell>)
}