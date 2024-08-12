package com.example.historyapp.ui.fragment.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.historyapp.data.model.HistoryModel
import com.example.historyapp.databinding.ItemHistoryBinding

class HistoryAdapter(private val onItemClick: (HistoryModel) -> Unit) :
    ListAdapter<HistoryModel, HistoryAdapter.HistoryViewHolder>(HistoryDiffCallback()) {

    inner class HistoryViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(historyModel: HistoryModel) {
            binding.tvTitle.text = historyModel.title
            binding.tvDescription.text = historyModel.description
            binding.root.setOnClickListener { onItemClick(historyModel) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

    class HistoryDiffCallback : DiffUtil.ItemCallback<HistoryModel>() {
    override fun areItemsTheSame(oldItem: HistoryModel, newItem: HistoryModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: HistoryModel, newItem: HistoryModel): Boolean {
        return oldItem == newItem
    }
}