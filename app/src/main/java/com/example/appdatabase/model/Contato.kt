package com.example.appdatabase.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contato (
    val nome: String,
    val email: String,
    val idade: Int?,
    val valor: Double?
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}