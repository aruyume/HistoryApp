package com.example.historyapp.ui.fragment.lists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.historyapp.data.model.ListsModel
import com.example.historyapp.databinding.ItemListsBinding

class ListsAdapter(
    private val onItemClick: (ListsModel) -> Unit
) : ListAdapter<ListsModel, ListsAdapter.ListViewHolder>(ListsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemListsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }

    class ListViewHolder(private val binding: ItemListsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ListsModel, onItemClick: (ListsModel) -> Unit) {
            binding.tvTitle.text = item.title
            binding.root.setOnClickListener { onItemClick(item) }
        }
    }

    class ListsDiffCallback : DiffUtil.ItemCallback<ListsModel>() {
        override fun areItemsTheSame(oldItem: ListsModel, newItem: ListsModel): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: ListsModel, newItem: ListsModel): Boolean {
            return oldItem == newItem
        }
    }
}