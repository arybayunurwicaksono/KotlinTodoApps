package com.dguitarclassic.todoapps.model


import androidx.room.Database
import androidx.room.RoomDatabase
import com.dguitarclassic.todoapps.db.TodoDao

@Database(entities = [Todo::class], version = 1)
abstract class TodoDB : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}