package com.dguitarclassic.todoapps.repo

import android.app.Application
import androidx.lifecycle.LiveData
import com.dguitarclassic.todoapps.db.TodoDao
import com.dguitarclassic.todoapps.model.Todo
import com.dguitarclassic.todoapps.model.TodoDB
import javax.inject.Inject

class TodoRepo @Inject constructor(application: Application) {

    private val todoDao: TodoDao

    init {
        val db = TodoDB.getDatabase(application)
        todoDao = db.todoDao()
    }

    fun getAllTodo() : LiveData<List<Todo>> = todoDao.getAllTodo()

    fun addTodo(todo: Todo) = todoDao.addTodo(todo)

}