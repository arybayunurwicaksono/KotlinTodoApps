package com.dguitarclassic.todoapps.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dguitarclassic.todoapps.model.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo ORDER BY id DESC")
    fun getAllToDos(): Flow<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(toDo: Todo)

    @Query("DELETE FROM todo WHERE id LIKE :id")
    fun delete(id: Int)

}