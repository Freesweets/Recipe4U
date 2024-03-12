package com.example.recipe4u.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import com.example.recipe4u.MainViewModel
import com.example.recipe4u.R
import com.example.recipe4u.adapter.EinkaufsAdapter
import com.example.recipe4u.databinding.FragmentEinkaufslisteBinding
import com.example.recipe4u.modul.Einkauf
import android.view.inputmethod.InputMethodManager

class EinkaufslisteFragment : Fragment() {
    private lateinit var binding: FragmentEinkaufslisteBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEinkaufslisteBinding.inflate(layoutInflater)
        viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.einkauf.observe(viewLifecycleOwner) {
            binding.rvEinkauf.adapter = EinkaufsAdapter(viewModel, it)
        }
        binding.btnSend.setOnClickListener{
            if (binding.tietEinkaufsliste.text.toString() != ""){
            viewModel.insertEinkauf(Einkauf(text = binding.tietEinkaufsliste.text.toString()))
            hidekeyboard(view)
            binding.tietEinkaufsliste.text?.clear()
        }}

    }
    fun hidekeyboard(view: View){
        val inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0)
    }
}