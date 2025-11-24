package com.example.gamebarato.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity

data class JogoVitrine(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nomeJogo: String,
    val precoJogo: Double,
    val img: String
)
