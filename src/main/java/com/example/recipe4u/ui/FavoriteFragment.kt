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
import com.example.recipe4u.adapter.FavoritAdapter
import com.example.recipe4u.databinding.FragmentFavoriteBinding

class FavoriteFragment: Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(layoutInflater)
        viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.favRecipe.observe(viewLifecycleOwner){
            binding.rvFavorit.adapter = FavoritAdapter(viewModel, it)
        }
        binding.fabDelte.load(R.drawable.icons8_delete_50)
        binding.fabDelte.setOnClickListener{
        viewModel.deleteFavRecipe()
        }
    }
}