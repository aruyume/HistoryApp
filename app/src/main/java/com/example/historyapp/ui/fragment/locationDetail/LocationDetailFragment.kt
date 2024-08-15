package com.example.historyapp.ui.fragment.locationDetail

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.historyapp.data.model.LocationModel
import com.example.historyapp.databinding.FragmentLocationDetailBinding
import com.example.historyapp.ui.fragment.base.fragments.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class LocationDetailFragment : BaseFragment<FragmentLocationDetailBinding, LocationDetailViewModel>(
    FragmentLocationDetailBinding::inflate
) {
    private val args: LocationDetailFragmentArgs by navArgs()
    private val locationDetailViewModel: LocationDetailViewModel by viewModel()
    private var isModified = false

    override val viewModel: LocationDetailViewModel
        get() = locationDetailViewModel

    override fun setupObservers() = with(binding) {
        super.setupObservers()
        viewModel.loadLocation(args.locationId)
        viewModel.location.observe(viewLifecycleOwner) { heroes ->
            heroes?.let {
                etLocation.setText(it.location)
                isModified = it.location != viewModel.location.value?.location
                btnSave.visibility =
                    if (args.locationId == 0 || isModified) View.VISIBLE else View.GONE
            }
        }
    }

    override fun setupClickListeners() = with(binding) {
        super.setupClickListeners()
        btnSave.setOnClickListener {
            val location = etLocation.text.toString()
            val locationId = if (args.locationId == 0) 0 else args.locationId

            val locationModel = LocationModel(
                id = locationId,
                location = location
            )
            viewModel.saveLocation(locationModel)
            findNavController().navigateUp()
        }
        imgBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun initViews() = with(binding) {
        super.initViews()
        etLocation.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val location = etLocation.text.toString()
                isModified = location != (viewModel.location.value?.location ?: "")
                btnSave.visibility =
                    if (location.isNotEmpty() && (args.locationId == 0 || isModified)) {
                        View.VISIBLE
                    } else {
                        View.GONE
                    }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}