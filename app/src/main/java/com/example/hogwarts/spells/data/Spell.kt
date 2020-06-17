package com.example.hogwarts.spells.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "spells")
data class Spell (
    @PrimaryKey
    @field:SerializedName("_id") val _id: String,
    @field:SerializedName("spell") val spell: String,
    @field:SerializedName("type") val type: String,
    @field:SerializedName("effect") val effect: String
) {

}