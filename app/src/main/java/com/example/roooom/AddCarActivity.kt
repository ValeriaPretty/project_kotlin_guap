package com.example.roooom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.roooom.databinding.ActivityAddCarBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddCarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddCarBinding
    private lateinit var carDao: CarDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = CarDatabase.getDatabase(this)
        carDao = db.carDao()

        binding.btnAdd.setOnClickListener {
            val name = binding.etCarName.text.toString()
            val year = binding.etCarYear.text.toString().toIntOrNull()

            if (name.isNotEmpty() && year != null) {
                lifecycleScope.launch(Dispatchers.IO) {
                    carDao.insertCar(Car(name, year))
                }
                finish()
            }
        }
    }
}

