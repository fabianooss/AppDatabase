package com.example.appdatabase.dao

import androidx.room.*
import com.example.appdatabase.model.Contato

@Dao
interface ContatoDao {

    @Insert()
    suspend fun insert(contato: Contato)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(contato: Contato)

    @Delete
    suspend fun delete(contato: Contato)

    @Query("select * from Contato order by nome")
    suspend fun findAll(): List<Contato>

    @Query("select * from Contato c where c.id = :id")
    suspend fun findById(id: Int): Contato?

}