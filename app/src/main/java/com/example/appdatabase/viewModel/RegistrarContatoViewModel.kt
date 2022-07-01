package com.example.appdatabase.viewModel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appdatabase.model.Contato
import com.example.appdatabase.repository.ContatoRepository
import kotlinx.coroutines.launch

enum class Genero {
    Masculino, Feminino
}

class RegistrarContatoViewModel(
    private val repository: ContatoRepository
): ViewModel() {

    var nome by mutableStateOf("")
    var email by mutableStateOf("")
    var idade by mutableStateOf(0)
    var valor by mutableStateOf(0.0)
    var genero by mutableStateOf(Genero.Masculino)

    fun salvar() {
        val contato = Contato(nome, email,idade, valor)
        viewModelScope.launch {
            repository.save(contato)
        }



    }

}