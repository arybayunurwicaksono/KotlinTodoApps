package com.dguitarclassic.todoapps.domain.usecase

import com.dguitarclassic.todoapps.model.Todo
import com.dguitarclassic.todoapps.repo.TodoRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllTodoUsecase @Inject constructor(private val repository: TodoRepo) {
    operator fun invoke(): Flow<List<Todo>> = repository.getAllTodo()
}

