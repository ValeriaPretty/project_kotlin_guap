package com.example.roooom

import android.content.Intent
import com.example.roooom.databinding.ActivityCarListBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

class CarListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCarListBinding
    private lateinit var carDao: CarDao
    private lateinit var adapter: CarAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener{close()}

        val db = CarDatabase.getDatabase(this)
        carDao = db.carDao()

        adapter = CarAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        carDao.getAllCars().observe(this) { cars ->
            adapter.submitList(cars)
        }
    }
    fun close() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}

