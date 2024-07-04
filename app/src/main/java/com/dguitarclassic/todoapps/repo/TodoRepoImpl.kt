package com.dguitarclassic.todoapps.repo

import com.dguitarclassic.todoapps.db.TodoDao
import com.dguitarclassic.todoapps.model.Todo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TodoRepoImpl @Inject constructor(
    private val todoDao: TodoDao
) : TodoRepo{
    override fun GetAllTodo(): Flow<List<Todo>> = todoDao.getAllToDos()

    override suspend fun insert(todo: Todo) = todoDao.insert(todo)

    override fun delete(id: Int) = todoDao.delete(id)
}