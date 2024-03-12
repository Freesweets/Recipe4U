package com.example.recipe4u.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.recipe4u.MainViewModel
import com.example.recipe4u.R
import com.example.recipe4u.databinding.ItemRecipeBinding
import com.example.recipe4u.modul.FavRecipe

class FavoritAdapter(
    private val viewModel: MainViewModel,
    private val dataset: MutableList<FavRecipe>
) : RecyclerView.Adapter<FavoritAdapter.FavoritViewHolder>() {
    inner class FavoritViewHolder(val binding: ItemRecipeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoritAdapter.FavoritViewHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoritViewHolder(binding)
    }
    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: FavoritViewHolder, position: Int) {
        val item = dataset[position]
        holder.binding.ivRecipePic.load(item.image)
        holder.binding.tvRecipeNameItem.text = item.title

        holder.binding.cvRecipeItem.setOnClickListener {
            viewModel.currentId = item.recipeId
            viewModel.setCurrentFavRecipe(item)
            holder.itemView.findNavController().navigate(R.id.recipeDetailFavFragment)
        }
    }


}