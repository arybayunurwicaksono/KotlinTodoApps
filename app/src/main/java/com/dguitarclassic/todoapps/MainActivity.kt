package com.dguitarclassic.todoapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dguitarclassic.todoapps.databinding.ActivityMainBinding
import com.dguitarclassic.todoapps.model.Todo
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appViewModel: AppViewModel
    lateinit var todoList: List<Todo>
    private lateinit var adapter: TodoAdapter
    private lateinit var rvTodo: RecyclerView
    lateinit var todoArrayList: ArrayList<Todo>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appViewModel = obtainViewModel(this)
        try {
            appViewModel.addTodo(Todo(0,"1 Testing", "desc Testing","due Testing"))
            val result = appViewModel.getAllTodo()
            Log.e("DBTESTING", "${result.value?.get(1)}")
        } catch (e: Exception) {
            Log.e("DBTESTING", e.message.toString())
        }
        todoArrayList = ArrayList()
        setRecyclerView()
    }

    private fun obtainViewModel(activity: AppCompatActivity): AppViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(AppViewModel::class.java)
    }

    private fun setRecyclerView() {
        rvTodo = binding.rvTodo
        rvTodo.layoutManager = LinearLayoutManager(this)
        rvTodo.setHasFixedSize(true)
        appViewModel.getAllTodo().observe(this) { itemList ->
            if (itemList != null) {
                todoList = itemList
                adapter.setTodoData(itemList)
            }
        }
        adapter = TodoAdapter(todoList)
        rvTodo.adapter = adapter
    }
}