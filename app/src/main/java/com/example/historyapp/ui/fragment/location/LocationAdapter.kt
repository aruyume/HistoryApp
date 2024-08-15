package com.example.historyapp.ui.fragment.location

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.historyapp.data.model.LocationModel
import com.example.historyapp.databinding.ItemLocationBinding

class LocationAdapter(
    private val onLocationClick: (LocationModel) -> Unit
) : ListAdapter<LocationModel, LocationAdapter.LocationViewHolder>(LocationDiffCallback()) {

    inner class LocationViewHolder(private val binding: ItemLocationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val location = getItem(adapterPosition)
                onLocationClick(location)
            }
        }

        fun bind(location: LocationModel) {
            binding.checkBoxLocation.text = location.location
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val binding =
            ItemLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class LocationDiffCallback : DiffUtil.ItemCallback<LocationModel>() {
    override fun areItemsTheSame(oldItem: LocationModel, newItem: LocationModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: LocationModel, newItem: LocationModel): Boolean {
        return oldItem == newItem
    }
}