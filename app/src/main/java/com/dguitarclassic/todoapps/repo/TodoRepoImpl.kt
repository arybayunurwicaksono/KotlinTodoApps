package com.dguitarclassic.todoapps.repo

import com.dguitarclassic.todoapps.db.TodoDao
import com.dguitarclassic.todoapps.model.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TodoRepoImpl @Inject constructor(
    private val todoDao: TodoDao
) : TodoRepo {
    override fun getAllTodo(): Flow<List<Todo>> = todoDao.getAllToDos()

    override suspend fun insert(todo: Todo) {
        withContext(Dispatchers.IO) {
            todoDao.insert(todo)
        }
    }

    override suspend fun update(todo: Todo) {
        withContext(Dispatchers.IO) {
            todoDao.update(todo)
        }
    }

    override suspend fun delete(todo: Todo) {
        withContext(Dispatchers.IO) {
            todoDao.delete(todo)

        }
    }
}