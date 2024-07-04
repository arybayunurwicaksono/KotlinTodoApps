package com.dguitarclassic.todoapps

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.dguitarclassic.todoapps.databinding.ActivityTodoFormBinding
import com.dguitarclassic.todoapps.model.Todo
import com.dguitarclassic.todoapps.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class TodoFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTodoFormBinding
    private val viewModel: TodoViewModel by viewModels()
    private var todo: Todo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodoFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()

    }

    fun setupView() {
        binding.btnAddTodo.setOnClickListener {
            val title = binding.edTodoTitle.text.toString()
            val desc = binding.edTodoDesc.text.toString()
            val due = binding.edTodoDue.text.toString()

            if (title.isEmpty()) {
                binding.edTodoTitle.error = "Title is empty"
                return@setOnClickListener
            }
            if (desc.isEmpty()) {
                binding.edTodoDesc.error = "Description is empty"
                return@setOnClickListener
            }
            if (due.isEmpty()) {
                binding.edTodoDue.error = "Due date is empty"
                return@setOnClickListener
            }

            val newToDo = todo?.copy(
                title = title,
                desc = desc,
                due = due
            ) ?: Todo(
                id = 0,
                title = title,
                desc = desc,
                due = due
            )
            lifecycleScope.launch {
                viewModel.insert(newToDo)
            }
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()

        }
        binding.edTodoDue.setOnClickListener {
            showDatePickerDialog(it)
        }
    }

    private fun getCurrentDate(): String {
        val formatter = SimpleDateFormat("dd - MM - yyyy", Locale.getDefault())
        return formatter.format(Date())
    }

    fun showDatePickerDialog(v: View) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(
            this,
            { view, year, monthOfYear, dayOfMonth ->
                val dat = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                binding.edTodoDue.setText(dat)
            },
            year,
            month,
            day
        )
        datePickerDialog.show()

    }
}