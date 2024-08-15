package com.example.historyapp.ui.fragment.heroes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.historyapp.data.model.HeroModel
import com.example.historyapp.databinding.ItemHeroBinding

class HeroesAdapter(
    private val onHeroClick: (HeroModel) -> Unit
) : ListAdapter<HeroModel, HeroesAdapter.HeroesViewHolder>(HeroesDiffCallback()) {

    inner class HeroesViewHolder(private val binding: ItemHeroBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val hero = getItem(adapterPosition)
                onHeroClick(hero)
            }
        }

        fun bind(hero: HeroModel) {
            val fullName = "${hero.name} ${hero.surname}"
            binding.checkBoxHero.text = fullName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        val binding = ItemHeroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HeroesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class HeroesDiffCallback : DiffUtil.ItemCallback<HeroModel>() {
    override fun areItemsTheSame(oldItem: HeroModel, newItem: HeroModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: HeroModel, newItem: HeroModel): Boolean {
        return oldItem == newItem
    }
}