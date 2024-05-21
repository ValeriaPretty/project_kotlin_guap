package com.example.roooom

import android.content.Intent
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

        binding.back.setOnClickListener { close() }

        val db = CarDatabase.getDatabase(this)
        carDao = db.carDao()

        binding.btnAdd.setOnClickListener {
            val name = binding.etCarName.text.toString()
            val year = binding.etCarYear.text.toString().toIntOrNull()
            val nalichie = binding.priceText.text.toString()
            val price = binding.nalichieText.text.toString()


            if (name.isNotEmpty() && year != null) {
                lifecycleScope.launch(Dispatchers.IO) {
                    carDao.insertCar(Car(name = name, year = year, nalichie = nalichie, price = price))
                }
                finish()
            }
        }
    }
    fun close() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}

