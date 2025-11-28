package com.example.gamebarato.Models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity

data class JogoVitrine(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nomeJogo: String,
    val precoJogo: String,
    val imgJogo: String
): Parcelable
