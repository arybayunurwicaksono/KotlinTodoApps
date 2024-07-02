package com.dguitarclassic.todoapps.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dguitarclassic.todoapps.model.Todo

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo ORDER BY id DESC")
    fun getAllTodo() : LiveData<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addTodo(todo: Todo)

}