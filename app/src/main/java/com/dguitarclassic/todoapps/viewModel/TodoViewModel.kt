package com.dguitarclassic.todoapps.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dguitarclassic.todoapps.domain.usecase.AddTodoUseCase
import com.dguitarclassic.todoapps.domain.usecase.DeleteTodoUseCase
import com.dguitarclassic.todoapps.domain.usecase.GetAllTodoUsecase
import com.dguitarclassic.todoapps.domain.usecase.UpdateTodoUseCase
import com.dguitarclassic.todoapps.model.Todo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val getAllTodoUsecase: GetAllTodoUsecase,
    private val addTodoUseCase: AddTodoUseCase,
    private val updateTodoUseCase: UpdateTodoUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase
) : ViewModel() {

    val allTodo: StateFlow<List<Todo>> = getAllTodoUsecase()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    suspend fun insert(title: String, desc: String, due: String) {
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
        viewModelScope.launch {
            updateTodoUseCase(
                Todo(
                    id = id,
                    title = title,
                    desc = desc,
                    due = due
                )
            )
        }
    }

    suspend fun delete(todo: Todo) {
        viewModelScope.launch {
            deleteTodoUseCase(todo)
        }
    }
}