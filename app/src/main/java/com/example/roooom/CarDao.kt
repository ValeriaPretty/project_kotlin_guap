package com.example.roooom

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CarDao {
    @Query("SELECT * FROM cars")
    fun getAllCars(): LiveData<List<Car>>

    @Insert
    suspend fun insertCar(car: Car)

    @Update
    suspend fun updateCar(car: Car)

    @Query("DELETE FROM cars WHERE id = :id")
    suspend fun deleteCarById(id: Long)
}
