package com.example.historyapp.ui.fragment.history

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.historyapp.R
import com.example.historyapp.data.model.HistoryModel
import com.example.historyapp.databinding.FragmentHistoryDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryDetailFragment : Fragment() {

    private lateinit var binding: FragmentHistoryDetailBinding
    private val args: HistoryDetailFragmentArgs by navArgs()
    private val historyDetailViewModel: HistoryDetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
        setupTextWatcher()
        loadData()
    }

    private fun loadData() {
        val historyId = args.historyId
        historyDetailViewModel.loadHistory(historyId)
        historyDetailViewModel.history.observe(viewLifecycleOwner) { history ->
            if (history != null) {
                binding.etTitle.setText(history.title)
                binding.etDescription.setText(history.description)
            }
        }
    }

    private fun setupListener() {
        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val description = binding.etDescription.text.toString()
            val historyModel = HistoryModel(id = args.historyId, title = title, description = description)

            historyDetailViewModel.saveHistory(historyModel)

            // Navigate back to HistoryFragment
            findNavController().navigate(R.id.action_historyDetailFragment_to_historyFragment)
        }

        binding.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }


    private fun setupTextWatcher() {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Enable the Save button only if both title and description are not empty
                binding.btnSave.isEnabled = binding.etTitle.text.isNotEmpty() && binding.etDescription.text.isNotEmpty()
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        binding.etTitle.addTextChangedListener(textWatcher)
        binding.etDescription.addTextChangedListener(textWatcher)
    }
}
