package com.dguitarclassic.todoapps.domain.usecase

import com.dguitarclassic.todoapps.model.Todo
import com.dguitarclassic.todoapps.repo.TodoRepo
import javax.inject.Inject

class UpdateTodoUseCase @Inject constructor(private val repository: TodoRepo) {
    suspend operator fun invoke(todo: Todo) = repository.update(todo)
}