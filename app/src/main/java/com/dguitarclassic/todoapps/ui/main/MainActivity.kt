package com.dguitarclassic.todoapps.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dguitarclassic.todoapps.AppConst
import com.dguitarclassic.todoapps.databinding.ActivityMainBinding
import com.dguitarclassic.todoapps.ui.form.TodoAdapter
import com.dguitarclassic.todoapps.ui.form.TodoFormActivity
import com.dguitarclassic.todoapps.viewModel.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: TodoViewModel by viewModels()

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
        val adapter = TodoAdapter { todo ->
            val intent = Intent(this, TodoFormActivity::class.java).apply {
                putExtra(AppConst.TODO, todo)
            }
            startActivity(intent)
        }
        binding.rvTodo.adapter = adapter
        binding.rvTodo.layoutManager = LinearLayoutManager(this)
        viewModel.allToDos.observe(this) {
            adapter.submitList(it) {
                binding.rvTodo.scrollToPosition(0)
            }
        }
    }
}