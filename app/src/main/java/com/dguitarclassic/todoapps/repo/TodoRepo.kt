package com.dguitarclassic.todoapps.repo

import com.dguitarclassic.todoapps.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepo {
    fun GetAllTodo(): Flow<List<Todo>>

    suspend fun insert(todo: Todo)

    fun delete(id: Int)
}