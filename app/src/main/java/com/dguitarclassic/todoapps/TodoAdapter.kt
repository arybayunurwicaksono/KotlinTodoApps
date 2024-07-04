package com.dguitarclassic.todoapps

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dguitarclassic.todoapps.databinding.ItemRowTodoBinding
import com.dguitarclassic.todoapps.model.Todo

class TodoAdapter(private var todoList: List<Todo>) :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(val binding: ItemRowTodoBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ItemRowTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val listTodo = todoList[position]
        holder.binding.todoTitle.text = "${listTodo.id} : ${listTodo.title}"
        holder.binding.todoDesc.text = listTodo.desc
        holder.binding.todoDue.text = listTodo.due
    }


    override fun getItemCount(): Int = todoList.size


    fun setTodoData(postList: List<Todo>) {

        this.todoList = postList
        notifyDataSetChanged()

    }

}