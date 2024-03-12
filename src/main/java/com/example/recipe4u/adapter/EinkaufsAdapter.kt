package com.example.recipe4u.adapter

import android.service.autofill.Dataset
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.recipe4u.MainViewModel
import com.example.recipe4u.R
import com.example.recipe4u.databinding.FragmentEinkaufslisteBinding
import com.example.recipe4u.databinding.ItemTextInputBinding
import com.example.recipe4u.modul.Einkauf
import com.example.recipe4u.ui.EinkaufslisteFragment

class EinkaufsAdapter(
    private val viewModel: MainViewModel,
    private val dataset: MutableList<Einkauf>,
): RecyclerView.Adapter<EinkaufsAdapter.EinkaufViewHolder>() {
    inner class EinkaufViewHolder(val binding: ItemTextInputBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EinkaufsAdapter.EinkaufViewHolder {
        val binding = ItemTextInputBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EinkaufViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: EinkaufViewHolder, position: Int) {
        val item = dataset[position]
        holder.binding.tvInput.text = item.text
        //holder.binding.ibDelteItem.load(R.drawable.icons8_trash_can_layout_for_a_indication_to_throw_trash_48)
        holder.binding.ibDelteItem.setOnClickListener{
            viewModel.deleteById(item)
        }

    }
}