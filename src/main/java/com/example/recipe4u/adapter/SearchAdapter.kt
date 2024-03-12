package com.example.recipe4u.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.recipe4u.MainViewModel
import com.example.recipe4u.R
import com.example.recipe4u.databinding.ItemRecipeBinding
import com.example.recipe4u.modul.RecipeResult

class SearchAdapter(
    private val viewModel: MainViewModel,
    private val dataset: List<RecipeResult>
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    inner class SearchViewHolder(val binding: ItemRecipeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = dataset[position]
        holder.binding.tvRecipeNameItem.text = item.title
        holder.binding.ivRecipePic.load(item.image)
        holder.binding.cvRecipeItem.setOnClickListener{
            viewModel.currentId = item.id
            holder.itemView.findNavController().navigate(R.id.recipeDetailFragment)
        }
    }


}