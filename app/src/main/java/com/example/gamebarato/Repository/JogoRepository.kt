package com.example.gamebarato.Repository

import com.example.gamebarato.Models.JogoVitrine
import com.example.gamebarato.Source.JogoDao

interface JogoRepository {
    suspend fun mostrarCartoes(): List<JogoVitrine>
    suspend fun inserirNovoJogo(nome: String, preco: String, imagem: String): List<JogoVitrine>
}

class jogoRepositoryImplementacao(private val jogoDao: JogoDao): JogoRepository{
    override suspend fun mostrarCartoes() = jogoDao.obterCartoes()

    override suspend fun inserirNovoJogo(
        nome: String,
        preco: String,
        imagem: String
    ): List<JogoVitrine> {
        val jogoVitrine = JogoVitrine(nomeJogo = nome, precoJogo = preco, img = imagem)
        jogoDao.adicionar(jogoVitrine)
        return mostrarCartoes()
    }
}