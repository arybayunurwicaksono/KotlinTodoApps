package com.dguitarclassic.todoapps.repo

import com.dguitarclassic.todoapps.db.TodoDao
import com.dguitarclassic.todoapps.model.Todo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TodoRepoImpl @Inject constructor(
    private val todoDao: TodoDao
) : TodoRepo {
    override fun getAllTodo(): Flow<List<Todo>> = todoDao.getAllToDos()

    override suspend fun insert(todo: Todo) = todoDao.insert(todo)
    override suspend fun update(todo: Todo) = todoDao.update(todo)

    override suspend fun delete(todo: Todo) = todoDao.delete(todo)
}