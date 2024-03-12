package com.example.recipe4u.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.recipe4u.MainViewModel
import com.example.recipe4u.R
import com.example.recipe4u.databinding.ItemCategoryBinding
import com.example.recipe4u.modul.RecipeResult

class CategoryResultAdapter(
    private val viewModel: MainViewModel,
    private val dataset: List<RecipeResult>
) : RecyclerView.Adapter<CategoryResultAdapter.CategoryViewHolder>() {
    inner class CategoryViewHolder(val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryResultAdapter.CategoryViewHolder {
        val binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = dataset[position]
        holder.binding.ivRecipePic.load(item.image)
        holder.binding.tvRecipeNameItem.text = item.title

        holder.binding.cvRecipeItem.setOnLongClickListener{
            viewModel.deleteFavRecipe()
            println()
            true
        }

        holder.binding.cvRecipeItem.setOnClickListener {
            viewModel.currentId = item.id
            holder.itemView.findNavController().navigate(R.id.recipeDetailFragment)
        }
    }

}