package com.example.gamebarato.Models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamebarato.Repository.JogoRepository
import kotlinx.coroutines.launch

class JogoViewModel(private val jogoRepository: JogoRepository): ViewModel() {

    private val jogo = MutableLiveData<List<JogoVitrine>>()

    fun watcherAll(): LiveData<List<JogoVitrine>> = jogo

    private val errado = MutableLiveData<String>()

    fun watcherErrado(): LiveData<String> = errado

    fun mostrarJogos() {
        viewModelScope.launch {
            try {
                jogo.value = jogoRepository.mostrarCartoes()
            } catch (e: Exception){
                errado.value = e.message
            }
        }
    }

    fun inserirJogo(nome: String, preco: String, imagem: String){
        viewModelScope.launch {
            try {
                val resultado = jogoRepository.inserirNovoJogo(nome,preco,imagem)
                jogo.value = resultado
            }catch (e: Exception){
                errado.value = e.message
            }
        }
    }
}