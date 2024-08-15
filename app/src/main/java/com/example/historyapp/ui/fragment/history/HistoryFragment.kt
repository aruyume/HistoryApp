package com.example.historyapp.ui.fragment.history

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.historyapp.data.model.HistoryModel
import com.example.historyapp.databinding.FragmentHistoryBinding
import com.example.historyapp.ui.fragment.base.fragments.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : BaseFragment<FragmentHistoryBinding, HistoryViewModel>(
    FragmentHistoryBinding::inflate
) {

    private val historyAdapter by lazy {
        HistoryAdapter { history ->
            onClicked(history)
        }
    }

    override val viewModel: HistoryViewModel by viewModel()

    override fun setupClickListeners() {
        super.setupClickListeners()
        with(binding) {
            btnAdd.setOnClickListener {
                val action =
                    HistoryFragmentDirections.actionHistoryFragmentToHistoryDetailFragment(0)
                findNavController().navigate(action)
            }
            imgClearHistory.setOnClickListener {
                showAlertDialog(
                    title = "Clear history",
                    message = "Do you really want to clear the history?",
                    onPositiveClick = {
                        viewModel.clearData()
                    }
                )
            }
            imgBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    override fun initViews() {
        super.initViews()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.rvHistory.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = historyAdapter
        }
    }

    override fun loadData() {
        viewModel.allData.observe(viewLifecycleOwner) { historyList ->
            historyAdapter.submitList(historyList)
        }
    }

    private fun onClicked(history: HistoryModel) {
        val action =
            HistoryFragmentDirections.actionHistoryFragmentToHistoryDetailFragment(history.id)
        findNavController().navigate(action)
    }
}