package com.dguitarclassic.todoapps.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dguitarclassic.todoapps.TodoAdapter
import com.dguitarclassic.todoapps.TodoFormActivity
import com.dguitarclassic.todoapps.TodoViewModel
import com.dguitarclassic.todoapps.databinding.ActivityMainBinding
import com.dguitarclassic.todoapps.model.Todo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: TodoViewModel by viewModels()
    lateinit var todoList: List<Todo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
    }

    fun setupView() {
        binding.fab.setOnClickListener {
            startActivity(Intent(this, TodoFormActivity::class.java))
        }
        setRecyclerView()
    }

    private fun setRecyclerView() {
        binding.rvTodo.layoutManager = LinearLayoutManager(this)
        binding.rvTodo.setHasFixedSize(true)
        lifecycleScope.launch {
            viewModel.allTodo.collect { data ->
                try {
                    val adapter = TodoAdapter(data)
                    binding.rvTodo.adapter = adapter
                    adapter.setTodoData(todoList)
                } catch (e: Exception) {
                    Log.e("hiltTesting", "$e")
                }
            }
        }
    }
}