package com.example.appdatabase.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appdatabase.model.Contato
import com.example.appdatabase.repository.ContatoRepository
import kotlinx.coroutines.launch

class ListContatoViewModel(
    private val rep: ContatoRepository): ViewModel() {

        var list: MutableState<List<Contato>> = mutableStateOf(listOf())

        init {
            viewModelScope.launch {
                list.value = rep.findAll()
            }
        }
}