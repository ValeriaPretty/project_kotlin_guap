package com.example.roooom

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roooom.databinding.ItemCarBinding

class CarAdapter : ListAdapter<Car, CarAdapter.CarViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val binding = ItemCarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    inner class CarViewHolder(private val binding: ItemCarBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(car: Car) {
            binding.tvCarName.text = car.name
            binding.tvCarYear.text = car.year.toString()

            itemView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, EditCarActivity::class.java).apply {
                    putExtra("CAR_ID", car.id)
                    putExtra("CAR_NAME", car.name)
                    putExtra("CAR_YEAR", car.year)
                }
                context.startActivity(intent)
            }
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<Car>() {
        override fun areItemsTheSame(oldItem: Car, newItem: Car): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Car, newItem: Car): Boolean {
            return oldItem == newItem
        }
    }
}
