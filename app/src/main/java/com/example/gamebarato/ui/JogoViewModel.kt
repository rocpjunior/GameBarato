package com.example.gamebarato.ui

import androidx.lifecycle.*
import com.example.gamebarato.Models.JogoVitrine
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
}