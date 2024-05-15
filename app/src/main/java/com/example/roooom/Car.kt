package com.example.roooom
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "cars")
data class Car(
    val name: String,
    val year: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
