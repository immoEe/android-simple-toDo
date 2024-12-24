package com.example.todo2

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val tasks = mutableListOf<String>()
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = TaskAdapter(tasks) { position ->
            tasks.removeAt(position)
            adapter.notifyDataSetChanged()
            Toast.makeText(this, "Задача удалена", Toast.LENGTH_SHORT).show()
        }

        binding.rvTasks.layoutManager = LinearLayoutManager(this)
        binding.rvTasks.adapter = adapter

        binding.btnAddTask.setOnClickListener {
            val task = binding.etTask.text.toString()
            if (task.isNotBlank()) {
                tasks.add(task)
                adapter.notifyDataSetChanged()
                binding.etTask.text.clear()
            } else {
                Toast.makeText(this, "Введите задачу", Toast.LENGTH_SHORT).show()
            }
        }
    }
}