package com.example.hogwarts.model

data class House(
    val _id: String, val name: String, val mascot: String,
    val headOfHouse: String, val houseGhost: String, val founder: String,
    val __v: Int, val school: String, val members: ArrayList<String>,
    val values: ArrayList<String>, val colors: ArrayList<String>
)