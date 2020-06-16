package com.example.hogwarts.houses.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "houses")
data class House(
    @PrimaryKey
    @field:SerializedName("_id") val _id: String,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("mascot") val mascot: String,
    @field:SerializedName("headOfHouse") val headOfHouse: String,
    @field:SerializedName("houseGhost") val houseGhost: String,
    @field:SerializedName("founder") val founder: String,
    @field:SerializedName("__v") val __v: Int,
    @field:SerializedName("school") val school: String,
    @Embedded val members: ArrayList<String>,
    @Embedded val values: ArrayList<String>,
    @Embedded val colors: ArrayList<String>
) {
    override fun toString() = name
}