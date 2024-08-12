package com.example.historyapp.ui.fragment.lists

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.historyapp.R
import com.example.historyapp.databinding.FragmentListsBinding
import com.example.historyapp.data.model.ListsModel

class ListsFragment : Fragment() {

    private val binding by lazy {
        FragmentListsBinding.inflate(layoutInflater)
    }

    private lateinit var listsAdapter: ListsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() = with(binding) {
        val items = listOf(
            ListsModel("History", R.id.action_listsFragment_to_historyFragment)
        )

        listsAdapter = ListsAdapter { listModel ->
            findNavController().navigate(listModel.actionId)
        }

        rvLists.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = listsAdapter
        }

        listsAdapter.submitList(items)
    }
}