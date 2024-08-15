package com.example.historyapp.ui.fragment.location

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.historyapp.data.model.LocationModel
import com.example.historyapp.databinding.FragmentLocationBinding
import com.example.historyapp.ui.fragment.base.fragments.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class LocationFragment : BaseFragment<FragmentLocationBinding, LocationViewModel>(
    FragmentLocationBinding::inflate
) {

    private val locationAdapter by lazy {
        LocationAdapter { location ->
            onLocationClicked(location)
        }
    }

    override val viewModel: LocationViewModel by viewModel()

    override fun setupClickListeners() {
        super.setupClickListeners()
        with(binding) {
            btnAdd.setOnClickListener {
                val action =
                    LocationFragmentDirections.actionLocationFragmentToLocationDetailFragment(0)
                findNavController().navigate(action)
            }
            imgClearLocation.setOnClickListener {
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
        binding.rvLocation.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = locationAdapter
        }
    }

    override fun loadData() {
        viewModel.allData.observe(viewLifecycleOwner) { locationList ->
            locationAdapter.submitList(locationList)
        }
    }

    private fun onLocationClicked(location: LocationModel) {
        val result = location.location
        findNavController().previousBackStackEntry?.savedStateHandle?.set("selectedLocation", result)
        findNavController().popBackStack()
    }
}