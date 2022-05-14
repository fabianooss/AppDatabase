package com.example.appdatabase.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appdatabase.model.Contato

@Database(entities = arrayOf(Contato::class), version = 1 )
abstract class AppDatabaseConnection: RoomDatabase() {

    abstract fun contatoDao(): ContatoDao
    //abstract fun outroDao(): OutroDao

    // Desing Pattern - Singleton
    companion object {
        var connection: AppDatabaseConnection? = null

        fun getDB(context: Context): AppDatabaseConnection {
            val temp = connection
            if (temp != null) {
                return temp
            }
            else {
                // conectar o banco
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabaseConnection::class.java,
                    "meu-database"
                ).build()
                connection = instance
                return instance
            }
        }

    }
}