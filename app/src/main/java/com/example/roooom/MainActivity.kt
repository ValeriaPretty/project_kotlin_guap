package com.example.roooom
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.roooom.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddCar.setOnClickListener {
            startActivity(Intent(this, AddCarActivity::class.java))
        }

        binding.btnViewCars.setOnClickListener {
            startActivity(Intent(this, CarListActivity::class.java))
        }
    }
}
