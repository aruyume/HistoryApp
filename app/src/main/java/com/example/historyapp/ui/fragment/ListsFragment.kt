package com.example.historyapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.historyapp.R
import com.example.historyapp.databinding.FragmentListsBinding

class ListsFragment : Fragment() {

    private lateinit var binding: FragmentListsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        binding.btnHistory.setOnClickListener {
            findNavController().navigate(R.id.action_listsFragment_to_historyFragment)
        }
    }
}
