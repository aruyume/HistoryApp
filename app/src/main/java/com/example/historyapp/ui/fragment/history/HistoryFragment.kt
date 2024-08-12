package com.example.historyapp.ui.fragment.history

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.historyapp.R
import com.example.historyapp.app.App
import com.example.historyapp.data.db.AppDatabase
import com.example.historyapp.data.model.HistoryModel
import com.example.historyapp.databinding.FragmentHistoryBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private val historyViewModel: HistoryViewModel by viewModel()  // Initialize ViewModel
    private val historyAdapter by lazy {
        HistoryAdapter { history ->
            onClicked(history)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
        initializeRecyclerView()
        loadData()
    }

    private fun initializeRecyclerView() {
        binding.rvHistory.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = historyAdapter
        }
    }

    private fun setupListener() = with(binding) {
        btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_historyFragment_to_historyDetailFragment)
        }
        imgBack.setOnClickListener {
            findNavController().navigate(R.id.action_historyFragment_to_listsFragment)
        }
    }

    private fun loadData() {
        historyViewModel.getAllHistory().observe(viewLifecycleOwner) { historyList ->
            Log.d("HistoryFragment", "Loaded ${historyList.size} items")

            historyAdapter.submitList(historyList)
        }
    }

    private fun onClicked(history: HistoryModel) {
        val action = HistoryFragmentDirections.actionHistoryFragmentToHistoryDetailFragment(history.id)
        findNavController().navigate(action)
    }
}
