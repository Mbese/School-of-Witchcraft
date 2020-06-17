package com.example.hogwarts.characters.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "characters")
data class Character(
    @PrimaryKey
    @field:SerializedName("_id") val _id: String,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("role") val role: String,
    @field:SerializedName("school") val school: String,
    @field:SerializedName("__v") val __v: String,
    @field:SerializedName("ministryOfMagic") val ministryOfMagic: Boolean,
    @field:SerializedName("orderOfThePhoenix") val orderOfThePhoenix: Boolean,
    @field:SerializedName("dumbledoresArmy") val dumbledoresArmy: Boolean,
    @field:SerializedName("deathEater") val deathEater: Boolean,
    @field:SerializedName("bloodStatus") val bloodStatus: String,
    @field:SerializedName("species") val species: String
) {
    override fun toString() = name
}