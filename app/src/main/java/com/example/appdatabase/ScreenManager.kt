package com.example.appdatabase


import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ScreenManager(val route: String,
                           @StringRes val resourceId: Int,
                           val icon: ImageVector
) {
    object Home: ScreenManager("home", R.string.home, Icons.Filled.Home)
    object Cadastro : ScreenManager("cadastro", R.string.cadastro, Icons.Filled.Build)
    object About : ScreenManager("about", R.string.about, Icons.Filled.Face)
}