package com.example.gamebarato.Repository

import com.example.gamebarato.Models.JogoVitrine
import com.example.gamebarato.Source.JogoDao

interface JogoRepository {
    suspend fun mostrarCartoes(): List<JogoVitrine>
}

class jogoRepositoryImplementacao(private val jogoDao: JogoDao): JogoRepository{
    override suspend fun mostrarCartoes() = jogoDao.obterCartoes()
}