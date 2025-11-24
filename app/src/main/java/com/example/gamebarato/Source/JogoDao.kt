package com.example.gamebarato.Source

import androidx.room.*
import com.example.gamebarato.Models.JogoVitrine
@Dao
interface JogoDao {

    @Insert
    suspend fun adicionar(jogoVitrine: JogoVitrine)

    @Query("SELECT * FROM JogoVitrine ORDER BY  id")
    suspend fun  obterCartoes(): List<JogoVitrine>
}