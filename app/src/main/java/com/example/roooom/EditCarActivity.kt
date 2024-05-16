package com.example.roooom

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.roooom.databinding.ActivityEditCarBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditCarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditCarBinding
    private lateinit var carDao: CarDao
    private var carId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditCarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.back.setOnClickListener { close() }
        val db = CarDatabase.getDatabase(this)
        carDao = db.carDao()

        carId = intent.getLongExtra("CAR_ID", 0)
        val carName = intent.getStringExtra("CAR_NAME") ?: ""
        val carYear = intent.getIntExtra("CAR_YEAR", 0)

        binding.etCarName.setText(carName)
        binding.etCarYear.setText(carYear.toString())

        binding.btnDelete.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                carDao.deleteCarById(carId)
                finish()
            }
        }

        binding.btnUpdate.setOnClickListener {
            val name = binding.etCarName.text.toString()
            val year = binding.etCarYear.text.toString().toIntOrNull()

            if (name.isNotEmpty() && year != null) {
                lifecycleScope.launch(Dispatchers.IO) {
                    carDao.updateCar(Car(id = carId, name = name, year = year))
                    finish()
                }
            } else {
                Toast.makeText(this, "Пожалуйста, введите корректные данные", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun close() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
