package com.example.gamebarato

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gamebarato.Repository.JogoRepository

class JogoViewModelFactory (private val jogoRepository: JogoRepository): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(JogoRepository::class.java).newInstance(jogoRepository)
    }
}