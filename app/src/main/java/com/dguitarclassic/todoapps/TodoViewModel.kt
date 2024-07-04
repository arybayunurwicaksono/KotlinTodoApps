package com.dguitarclassic.todoapps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dguitarclassic.todoapps.domain.usecase.AddTodoUseCase
import com.dguitarclassic.todoapps.domain.usecase.GetAllTodoUsecase
import com.dguitarclassic.todoapps.model.Todo
import com.dguitarclassic.todoapps.repo.TodoRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val repository: TodoRepo,
    private val getAllTodoUsecase: GetAllTodoUsecase,
    private val addTodoUseCase: AddTodoUseCase
) : ViewModel() {

    val allTodo: StateFlow<List<Todo>> = getAllTodoUsecase()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    suspend fun insert(todo: Todo) {
        viewModelScope.launch {
            addTodoUseCase(todo)
        }
    }

    fun delete(id: Int) = viewModelScope.launch {
        repository.delete(id)
    }
}