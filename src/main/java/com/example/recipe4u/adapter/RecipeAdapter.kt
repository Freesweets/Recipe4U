package com.example.recipe4u.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe4u.MainViewModel
import com.example.recipe4u.databinding.ItemRecipeBinding
import com.example.recipe4u.modul.RecipeDetail
import coil.load
import com.example.recipe4u.R
import com.example.recipe4u.modul.RandomRecipe
import com.example.recipe4u.remote.BASE_URL

class RecipeAdapter(
    private val viewModel: MainViewModel,
    private val dataset: List<RecipeDetail>
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {
    inner class RecipeViewHolder(val binding: ItemRecipeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val item = dataset[position]

        //viewModel.getRecipeId(item.id)
        Log.e("Adapter", "${item.title}")
        viewModel.getRecipeId(item.id)


        holder.binding.tvRecipeNameItem.text = item.title
        holder.binding.ivRecipePic.load(item.image)
        holder.binding.cvRecipeItem.setOnClickListener{
            viewModel.currentId = item.id
            holder.itemView.findNavController().navigate(R.id.recipeDetailFragment)
        }
    }
}