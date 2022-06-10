package com.example.appdatabase.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appdatabase.repository.ContatoRepository

class LocalicarContatoViewModelFactory(val app: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val repository = ContatoRepository(app)
        val model = LocalizarContatoViewModel (repository)
        return model as T
    }


}