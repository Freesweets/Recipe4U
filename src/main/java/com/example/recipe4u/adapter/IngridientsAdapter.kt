package com.example.recipe4u.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe4u.MainViewModel
import com.example.recipe4u.databinding.ItemIngridientBinding
import com.example.recipe4u.modul.ExtendedIngredients

class IngridientsAdapter(
    private val viewModel: MainViewModel,
    private val dataset: List<ExtendedIngredients>
) : RecyclerView.Adapter<IngridientsAdapter.IngridientsViewHolder>() {
    inner class IngridientsViewHolder(val binding: ItemIngridientBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngridientsViewHolder {
        val binding =
            ItemIngridientBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IngridientsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: IngridientsViewHolder, position: Int) {
        val item = dataset[position]
        holder.binding.tvIngridientsOrigin.text = item.original
    }
}