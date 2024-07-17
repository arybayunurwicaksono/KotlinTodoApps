package com.dguitarclassic.todoapps

import com.dguitarclassic.todoapps.model.Todo

interface TodoClickListener {
    fun onTodoClick(todo: Todo)
}