package com.dguitarclassic.todoapps.ui.form

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import cn.pedant.SweetAlert.SweetAlertDialog
import com.dguitarclassic.todoapps.AppConst
import com.dguitarclassic.todoapps.R
import com.dguitarclassic.todoapps.databinding.ActivityTodoFormBinding
import com.dguitarclassic.todoapps.model.Todo
import com.dguitarclassic.todoapps.viewModel.TodoViewModel
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

    private fun setupView() {

        val dataTodo = intent.getParcelableExtra<Todo>(AppConst.TODO)
        if (dataTodo != null) {
            binding.edTodoTitle.setText(dataTodo.title)
            binding.edTodoDesc.setText(dataTodo.desc)
            binding.edTodoDue.setText(dataTodo.due)
            binding.btnAddTodo.text = getString(R.string.update)
            binding.btnDelete.visibility = VISIBLE
        } else {
            binding.edTodoDue.setText(getCurrentDate())
        }

        binding.btnAddTodo.setOnClickListener {
            val title = binding.edTodoTitle.text.toString()
            val desc = binding.edTodoDesc.text.toString()
            val due = binding.edTodoDue.text.toString()

            if (title.isEmpty()) {
                binding.edTodoTitle.error = getString(R.string.title_is_empty)
                return@setOnClickListener
            }
            if (desc.isEmpty()) {
                binding.edTodoDesc.error = getString(R.string.description_is_empty)
                return@setOnClickListener
            }
            if (due.isEmpty()) {
                binding.edTodoDue.error = getString(R.string.due_date_is_empty)
                return@setOnClickListener
            }

            if (dataTodo != null) {
                lifecycleScope.launch {
                    viewModel.update(dataTodo.id, title, desc, due)
                    finish()
                }
            } else {
                viewModel.insert(title, desc, due)
                finish()
            }
        }
        binding.edTodoDue.setOnClickListener {
            showDatePickerDialog(it)
        }
        binding.btnDelete.setOnClickListener {
            SweetAlertDialog(this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                .setTitleText(getString(R.string.are_you_sure_want_to_delete_this_todo))
                .setCustomImage(R.drawable.ic_baseline_info_24)
                .setConfirmText(getString(R.string.yes))
                .setConfirmButtonBackgroundColor(R.color.light_green)
                .setConfirmClickListener { sDialog ->
                    lifecycleScope.launch {
                        if (dataTodo != null) {
                            viewModel.delete(dataTodo)
                            finish()
                        }
                    }
                }
                .setCancelButtonBackgroundColor(R.color.light_green)
                .setCancelText(getString(R.string.no))
                .setCancelClickListener { pDialog ->
                    pDialog.dismissWithAnimation()
                }
                .show()
        }

    }

    private fun getCurrentDate(): String {
        val formatter = SimpleDateFormat("dd - MM - yyyy", Locale.getDefault())
        return formatter.format(Date())
    }

    private fun showDatePickerDialog(v: View) {
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