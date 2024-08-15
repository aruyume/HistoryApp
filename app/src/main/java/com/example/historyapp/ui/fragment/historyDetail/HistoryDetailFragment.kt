package com.example.historyapp.ui.fragment.historyDetail

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.historyapp.R
import com.example.historyapp.data.model.HistoryModel
import com.example.historyapp.databinding.FragmentHistoryDetailBinding
import com.example.historyapp.ui.fragment.base.fragments.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.io.IOException

class HistoryDetailFragment : BaseFragment<FragmentHistoryDetailBinding, HistoryDetailViewModel>(
    FragmentHistoryDetailBinding::inflate
) {
    private val args: HistoryDetailFragmentArgs by navArgs()
    private val historyDetailViewModel: HistoryDetailViewModel by viewModel()
    private var isModified = false
    private var imageUri: Uri? = null

    override val viewModel: HistoryDetailViewModel
        get() = historyDetailViewModel

    override fun setupObservers() = with(binding) {
        super.setupObservers()
        viewModel.loadHistory(args.historyId)
        viewModel.history.observe(viewLifecycleOwner) { history ->
            history?.let {
                etTitle.setText(it.title)
                etDescription.setText(it.description)
                isModified = it.title != viewModel.history.value?.title ||
                        it.description != viewModel.history.value?.description
                btnSave.visibility =
                    if (args.historyId == 0 || isModified) View.VISIBLE else View.GONE

                if (it.image != null) {
                    val imageFile = File(it.image)
                    if (imageFile.exists()) {
                        val bitmap = BitmapFactory.decodeFile(imageFile.absolutePath)
                        imgHistory.setImageBitmap(bitmap)
                    } else {
                        imgHistory.setImageResource(R.drawable.img_history)
                    }
                }
            }
        }
    }

    override fun setupClickListeners() = with(binding) {
        super.setupClickListeners()
        btnSave.setOnClickListener {
            val title = etTitle.text.toString()
            val description = etDescription.text.toString()
            val historyId = if (args.historyId == 0) 0 else args.historyId

            val historyModel = HistoryModel(
                id = historyId,
                title = title,
                description = description,
                image = imageUri?.path
            )
            viewModel.saveHistory(historyModel)
            findNavController().navigateUp()
        }
        imgBack.setOnClickListener {
            findNavController().navigateUp()
        }
        imgHistory.setOnClickListener {
            imageChooser()
        }
    }

    override fun initViews() = with(binding) {
        super.initViews()
        etTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val title = etTitle.text.toString()
                val description = etDescription.text.toString()
                isModified = title != (viewModel.history.value?.title ?: "") ||
                        description != (viewModel.history.value?.description ?: "")
                btnSave.visibility =
                    if (title.isNotEmpty() && description.isNotEmpty() && (args.historyId == 0 || isModified)) {
                        View.VISIBLE
                    } else {
                        View.GONE
                    }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        etDescription.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val title = etTitle.text.toString()
                val description = etDescription.text.toString()
                isModified = title != (viewModel.history.value?.title ?: "") ||
                        description != (viewModel.history.value?.description ?: "")
                btnSave.visibility =
                    if (title.isNotEmpty() && description.isNotEmpty() && (args.historyId == 0 || isModified)) {
                        View.VISIBLE
                    } else {
                        View.GONE
                    }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private val pickImageLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val selectedImageUri: Uri? = data?.data
                if (selectedImageUri != null) {
                    try {
                        val selectedImageBitmap = MediaStore.Images.Media.getBitmap(
                            requireContext().contentResolver,
                            selectedImageUri
                        )
                        binding.imgHistory.setImageBitmap(selectedImageBitmap)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    imageUri = selectedImageUri
                }
            }
        }

    private fun imageChooser() {
        val intent = Intent().apply {
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
        }
        pickImageLauncher.launch(Intent.createChooser(intent, "Select Picture"))
    }
}