package com.example.hogwarts.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hogwarts.characters.data.Character
import com.example.hogwarts.characters.data.CharacterDao
import com.example.hogwarts.houses.data.House
import com.example.hogwarts.houses.data.HouseDao
import com.example.hogwarts.spells.data.Spell
import com.example.hogwarts.spells.data.SpellDao

@Database(entities = [House::class, Character::class, Spell::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun houseDao(): HouseDao
    abstract fun characterDao(): CharacterDao
    abstract fun spellsDao(): SpellDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                synchronized(AppDatabase::class.java) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context.applicationContext, AppDatabase::class.java, "houses"
                        ).build()
                    }
                }
            }
            return instance!!
        }

    }
}