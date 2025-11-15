package com.example.gamebarato.Source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gamebarato.Models.JogoVitrine

@Database(entities = [JogoVitrine::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun JogoDao(): JogoDao
    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCKED = Any()

        operator fun invoke(context: Context)
        = instance ?: synchronized((LOCKED)) {
            instance ?: construirBancoDeDados(context).also { instance = it }
        }

        private fun construirBancoDeDados(context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "Jogo.db").build()
    }
}