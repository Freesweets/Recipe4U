package com.example.recipe4u.ui

import android.app.appsearch.SearchResult
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.recipe4u.MainViewModel
import com.example.recipe4u.R
import com.example.recipe4u.databinding.FragmentKategorieBinding

class KategorienFragment : Fragment() {

    private lateinit var binding: FragmentKategorieBinding
    private val viewModel: MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKategorieBinding.inflate(layoutInflater)
        viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivKetogenic.load(R.drawable.icons8_ketogenic_diet_100)
        binding.ivGlutenFree.load(R.drawable.icons8_gluten_free_100)
        binding.ivVegan.load(R.drawable.icons8_vegan_100)
        binding.ivVegetarian.load(R.drawable.icons8_vegetarian_100)
        binding.ivPaleo.load(R.drawable.icons8_paleo_diet_100)
        binding.ivLacto.load(R.drawable.icons8_no_eggs_100)
        binding.ivVegetarian.load(R.drawable.icons8_vegetarian_100)




        binding.cvKetogenic.setOnClickListener {
            viewModel.getCategory("ketogenic")
            findNavController().navigate(R.id.categoryResultFragment)
        }
        binding.cvGlutenFree.setOnClickListener {
            viewModel.getCategory("gluten free")
            findNavController().navigate(R.id.categoryResultFragment)

        }
        binding.cvPaleo.setOnClickListener {
            viewModel.getCategory("paleo")
            findNavController().navigate(R.id.categoryResultFragment)

        }
        binding.cvVegan.setOnClickListener {
            viewModel.getCategory("vegan")
            findNavController().navigate(R.id.categoryResultFragment)

        }
        binding.cvVegetarian.setOnClickListener {
            viewModel.getCategory("vegetarian")
            findNavController().navigate(R.id.categoryResultFragment)

        }
        binding.cvLactoVegeterian.setOnClickListener{
            viewModel.getCategory("lacto-vegetarian")
            findNavController().navigate(R.id.categoryResultFragment)
        }

    }

}