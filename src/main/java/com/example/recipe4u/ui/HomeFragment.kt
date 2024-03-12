package com.example.recipe4u.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.recipe4u.MainViewModel
import com.example.recipe4u.databinding.FragmentHomeBinding
import androidx.fragment.app.activityViewModels
import com.example.recipe4u.adapter.RecipeAdapter

class HomeFragment: Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: MainViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getRandomRecipe()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.randomRecipe.observe(viewLifecycleOwner){
            binding.rvHome.adapter = RecipeAdapter(viewModel, it.recipes)

        }
    }

}