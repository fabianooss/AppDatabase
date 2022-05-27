package com.example.appdatabase

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appdatabase.ui.theme.AppDatabaseTheme
import com.example.appdatabase.viewModel.RegistrarContatoViewModel
import com.example.appdatabase.viewModel.RegistrarContatoViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppDatabaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyDbApp()
                }
            }
        }
    }
}

@Composable
fun MyDbApp() {
    val ctx = LocalContext.current
    val app = ctx.applicationContext as Application
    val model:
            RegistrarContatoViewModel = viewModel(
                factory = RegistrarContatoViewModelFactory(app)
            )
    Column() {
        OutlinedTextField(
            value = model.nome,
            onValueChange = { model.nome = it },
            label = {
                Text(text = "Nome")
            }
        )
        OutlinedTextField(
            value = model.email ,
            onValueChange = { model.email = it},
            label = {
                Text(text = "E-mail")
            }
        )
        OutlinedTextField(
            value = model.idade?.toString(),
            onValueChange = {
              try {
                  model.idade = it.toInt()
              }
              catch (e: Exception) {
                  Log.e("app", "Erro conversão de idade" )
              }
            },
            label = {
                Text(text = "Idade")
            }
        )
        OutlinedTextField(
            value = model.valor?.toString(),
            onValueChange = {
                try {
                    model.valor = it.toDouble()
                }
                catch (e: Exception) {
                    Log.e("app", "Erro conversão de valor" )
                }
            } )
        Button(onClick = { model.salvar() }) {
            Text(text = "Salvar")
        }
    }


}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppDatabaseTheme {
        Greeting("Android")
    }
}