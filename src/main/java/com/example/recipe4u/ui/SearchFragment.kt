package com.example.recipe4u.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.recipe4u.MainViewModel
import com.example.recipe4u.adapter.CategoryResultAdapter
import com.example.recipe4u.adapter.RecipeAdapter
import com.example.recipe4u.adapter.SearchAdapter
import com.example.recipe4u.databinding.FragmentSearchBinding

class SearchFragment: Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.svInput.setOnQueryTextListener(object:OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.getRecipesSearches(query)
                return false
            }

        })

        viewModel.recipeSearches.observe(viewLifecycleOwner){
           binding.rvSearch.adapter = SearchAdapter(viewModel, it.results)
        }

    }
}