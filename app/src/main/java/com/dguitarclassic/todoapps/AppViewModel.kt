package com.dguitarclassic.todoapps

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dguitarclassic.todoapps.model.Todo
import com.dguitarclassic.todoapps.repo.TodoRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppViewModel@Inject constructor(application: Application): ViewModel() {

    private val todoRepo: TodoRepo = TodoRepo(application)

    fun getAllTodo() : LiveData<List<Todo>> = todoRepo.getAllTodo()

    fun addTodo(todo: Todo) = todoRepo.addTodo(todo)
}