package com.example.appdatabase.viewModel

import androidx.compose.runtime.*
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appdatabase.model.Contato
import com.example.appdatabase.repository.ContatoRepository
import io.reactivex.internal.operators.single.SingleDoOnSuccess
import kotlinx.coroutines.launch

class LocalizarContatoViewModel(
    private val repository: ContatoRepository
): ViewModel() {

    var id by mutableStateOf("")

    fun find(onSuccess: (Contato)-> Unit, onNotFound: () -> Unit) {

        viewModelScope.launch {
            if (id.isNotBlank() && id.isDigitsOnly()) {
                val contato = repository.findById(id.toInt())
                if (contato != null)
                    onSuccess(contato)
                else
                    onNotFound()

            }
            else {
                onNotFound()
            }
        }
    }

}