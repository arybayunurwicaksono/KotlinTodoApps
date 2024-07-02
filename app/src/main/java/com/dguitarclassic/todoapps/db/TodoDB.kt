package com.dguitarclassic.todoapps.model


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dguitarclassic.todoapps.db.TodoDao

@Database(entities =
[
    Todo::class
],
    version = 2)

abstract class TodoDB : RoomDatabase() {

    abstract fun todoDao(): TodoDao

    companion object {

        @Volatile
        private var INSTANCE: TodoDB? = null

        @JvmStatic
        fun getDatabase(context: Context): TodoDB {
            if(INSTANCE == null) {
                synchronized(TodoDB::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        TodoDB::class.java, "todoDB")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE as TodoDB
        }

    }
}