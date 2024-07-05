package com.dguitarclassic.todoapps.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dguitarclassic.todoapps.model.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo ORDER BY id DESC")
    fun getAllToDos(): Flow<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(toDo: Todo)

    @Update
    suspend fun update(todo: Todo)

    @Delete
    suspend fun delete(toDo: Todo)

}