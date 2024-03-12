package com.example.recipe4u.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import com.example.recipe4u.MainViewModel
import com.example.recipe4u.R
import com.example.recipe4u.adapter.IngridientsAdapter
import com.example.recipe4u.databinding.FragmentDetailViewBinding
import com.example.recipe4u.remote.BASE_URL

class RecipeDetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailViewBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailViewBinding.inflate(layoutInflater)
        viewModel.getRecipeId(viewModel.currentId)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.recipeDetail.observe(viewLifecycleOwner) { recipe ->
            var boolean = false
            binding.tvRecipeName.text = recipe.title
            binding.ivRecipeDetail.load(recipe.image)
            binding.tvReadyInMinutes.text = recipe.readyInMinutes.toString()
            binding.tvSrcName.text = recipe.sourceName
            binding.fabFavorit.load(R.drawable.icons8_favorite_50)
            binding.rvDetailView.adapter = IngridientsAdapter(viewModel, recipe.extendedIngredients)
            binding.fabFavorit.setOnClickListener {
                if (boolean == true) {
                    viewModel.deleteById()
                    boolean = false
                } else {
                    viewModel.insertFavRecipe(recipe)
                    binding.fabFavorit.load((R.drawable.icons8_heart_100))
                    boolean = true
                }
            }

            if (recipe.glutenFree) {
                binding.ivGluten.load(R.drawable.baseline_check_box_24)
            } else {
                binding.ivGluten.load(R.drawable.icon_no)
            }
            if (recipe.ketogenic) {
                binding.ivKetogenic.load(R.drawable.baseline_check_box_24)
            } else {
                binding.ivKetogenic.load(R.drawable.icon_no)
            }
            if (recipe.vegan) {
                binding.ivVegan.load(R.drawable.baseline_check_box_24)
            } else {
                binding.ivVegan.load(R.drawable.icon_no)
            }
            if (recipe.vegetarian) {
                binding.ivVegetarian.load(R.drawable.baseline_check_box_24)
            } else {
                binding.ivVegetarian.load(R.drawable.icon_no)
            }

           /* if (recipe.diet == "gluten free") {
                binding.ivDietDetailView.load(R.drawable.icons8_gluten_free_100)
            } else if (recipe.diet == "ketogenic") {
                binding.ivDietDetailView.load(R.drawable.icons8_ketogenic_diet_100)
            } else if (recipe.diet == "vegan") {
                binding.ivDietDetailView.load(R.drawable.icons8_vegan_100)
            } else if (recipe.diet == "vegetarian") {
                binding.ivDietDetailView.load(R.drawable.icons8_vegetarian_100)
            } else if (recipe.diet == "paleo") {
                binding.ivDietDetailView.load(R.drawable.icons8_paleo_diet_100)
            } else if (recipe.diet == "lacto-vegetarian") {
                binding.ivDietDetailView.load(R.drawable.icons8_no_eggs_100)
            }

            */
        }
    }
}