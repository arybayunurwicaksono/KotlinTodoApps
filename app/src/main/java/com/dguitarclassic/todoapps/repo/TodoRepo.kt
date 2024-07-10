package com.dguitarclassic.todoapps.repo

import com.dguitarclassic.todoapps.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepo {
    fun getAllTodo(): Flow<List<Todo>>
    suspend fun insert(todo: Todo)
    suspend fun update(todo: Todo)
    suspend fun delete(todo: Todo)
}