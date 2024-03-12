package com.example.recipe4u.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.recipe4u.MainViewModel
import com.example.recipe4u.adapter.CategoryResultAdapter
import com.example.recipe4u.databinding.FragmentKategorieResultBinding

class CategoryResultFragment : Fragment() {
    private lateinit var binding: FragmentKategorieResultBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKategorieResultBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.recipeSearches.observe(viewLifecycleOwner){
            binding.rvResultCategories.adapter = CategoryResultAdapter(viewModel, it.results)
        }
    }
}