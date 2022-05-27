package com.example.appdatabase.repository

import android.app.Application
import com.example.appdatabase.dao.AppDatabaseConnection
import com.example.appdatabase.dao.ContatoDao
import com.example.appdatabase.model.Contato

class ContatoRepository(app: Application) {

    private val contatoDao: ContatoDao

    init {
        contatoDao = AppDatabaseConnection
            .getDB(app).contatoDao()
    }

    suspend fun save(contato: Contato) {
        if (contato.id == 0) {
            contatoDao.insert(contato)
        }
        else {
            contatoDao.update(contato)
        }
    }

    suspend fun findAll(): List<Contato> = contatoDao.findAll()

    suspend fun findById(id: Int) = contatoDao.findById(id)

    suspend fun delete(contato: Contato) = contatoDao.delete(contato)



}