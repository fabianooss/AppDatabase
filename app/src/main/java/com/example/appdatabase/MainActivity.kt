package com.example.appdatabase

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.appdatabase.model.Contato
import com.example.appdatabase.ui.theme.AppDatabaseTheme
import com.example.appdatabase.viewModel.*

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
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    val items = listOf(
        ScreenManager.Home,
        ScreenManager.Cadastro,
        ScreenManager.Find
    )
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(screen.icon, contentDescription = null) },
                        label = { Text(stringResource(screen.resourceId)) },

                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {

                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(navController, startDestination = ScreenManager.Home.route, Modifier.padding(innerPadding)) {
            composable(ScreenManager.Home.route) { HomeCompose() }
            composable(ScreenManager.Find.route) { FindCompose() }
            composable(ScreenManager.Cadastro.route) { CadastroCompose() }
        }
    }

}

@Composable
fun HomeCompose() {
    val ctx = LocalContext.current
    val app = ctx.applicationContext as Application

    val model:
            ListContatoViewModel = viewModel(
        factory = ListContatoViewModelFactory(app)
    )
    val contatos = model.list.value

    LazyColumn(){
        items(items = contatos) {
            ContatoView(it)
        }
    }

}

@Composable
fun ContatoView(contato: Contato) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row() {
            Column(modifier = Modifier
                .padding(16.dp)
                .weight(1f)
            ) {
                Text(text = contato.nome)
                Text(
                    text = contato.email,
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }

}



@Composable
fun FindCompose() {
    val ctx = LocalContext.current
    val app = ctx.applicationContext as Application
    val model:
            LocalizarContatoViewModel = viewModel(
        factory = LocalicarContatoViewModelFactory(app)
    )
    Column() {
        OutlinedTextField(value = model.id, onValueChange = { model.id = it})
        Button(onClick = {
            model.find (
                onSuccess =  {
                     Toast.makeText(ctx, "Contato: ${it.nome}", Toast.LENGTH_SHORT).show()
                },
                onNotFound = {
                    Toast.makeText(ctx, "Contato não encontrado", Toast.LENGTH_SHORT).show()
                }
            )
        }) {
            Text(text = "Localizar")
        }
    }
}


@Composable
fun CadastroCompose() {
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