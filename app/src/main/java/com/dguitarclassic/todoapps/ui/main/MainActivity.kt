package com.dguitarclassic.todoapps.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dguitarclassic.todoapps.AppConst
import com.dguitarclassic.todoapps.ui.form.TodoAdapter
import com.dguitarclassic.todoapps.ui.form.TodoFormActivity
import com.dguitarclassic.todoapps.viewModel.TodoViewModel
import com.dguitarclassic.todoapps.databinding.ActivityMainBinding
import com.dguitarclassic.todoapps.model.Todo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: TodoViewModel by viewModels()
    private val onItemClick: (Todo) -> Unit = { toDo ->
        val intent = Intent(this, TodoFormActivity::class.java).apply {
            putExtra(AppConst.TODO, toDo)
        }
        startActivity(intent)
    }

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
        lifecycleScope.launch {
            viewModel.allTodo.collect { data ->
                binding.rvTodo.adapter = TodoAdapter(data, onItemClick)
            }
        }
    }
}