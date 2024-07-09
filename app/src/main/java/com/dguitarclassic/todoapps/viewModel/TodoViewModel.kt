package com.dguitarclassic.todoapps.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dguitarclassic.todoapps.domain.usecase.AddTodoUseCase
import com.dguitarclassic.todoapps.domain.usecase.DeleteTodoUseCase
import com.dguitarclassic.todoapps.domain.usecase.GetAllTodoUsecase
import com.dguitarclassic.todoapps.domain.usecase.UpdateTodoUseCase
import com.dguitarclassic.todoapps.model.Todo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val getAllTodoUsecase: GetAllTodoUsecase,
    private val addTodoUseCase: AddTodoUseCase,
    private val updateTodoUseCase: UpdateTodoUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase
) : ViewModel() {

    private val _allToDos = MutableLiveData<List<Todo>>()
    val allToDos: LiveData<List<Todo>> get() = _allToDos

    init {
        viewModelScope.launch {
            getAllTodoUsecase().collect { todos ->
                _allToDos.value = todos
            }
        }
    }

    fun insert(title: String, desc: String, due: String) {
        viewModelScope.launch {
            addTodoUseCase(
                Todo(
                    id = 0,
                    title = title,
                    desc = desc,
                    due = due
                )
            )
        }
    }

    suspend fun update(id: Int, title: String, desc: String, due: String) {
        updateTodoUseCase(
            Todo(
                id = id,
                title = title,
                desc = desc,
                due = due
            )
        )
    }

    suspend fun delete(todo: Todo) {
        viewModelScope.launch {
            deleteTodoUseCase(todo)
        }
    }
}