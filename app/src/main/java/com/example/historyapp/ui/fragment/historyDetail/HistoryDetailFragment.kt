package com.example.historyapp.ui.fragment.historyDetail

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.historyapp.data.model.HistoryModel
import com.example.historyapp.databinding.FragmentHistoryDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryDetailFragment : Fragment() {

    private val binding by lazy {
        FragmentHistoryDetailBinding.inflate(layoutInflater)
    }
    private val args: HistoryDetailFragmentArgs by navArgs()
    private val historyDetailViewModel: HistoryDetailViewModel by viewModel()

    private var isModified = false
    private val REQUEST_IMAGE_CAPTURE = 1
    private val REQUEST_IMAGE_PICK = 2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
        textWatcher()
        loadData()
    }

    private fun loadData() = with(binding) {
        val historyId = args.historyId
        historyDetailViewModel.loadHistory(historyId)
        historyDetailViewModel.history.observe(viewLifecycleOwner) { history ->
            history?.let {
                etTitle.setText(it.title)
                etDescription.setText(it.description)
                if (historyId == 0) {
                    btnSave.visibility = View.VISIBLE
                } else {
                    btnSave.visibility = View.GONE
                }
            }
        }
    }

    private fun setupListener() = with(binding) {
        btnSave.setOnClickListener {
            val title = etTitle.text.toString()
            val description = etDescription.text.toString()

            val historyId = if (args.historyId == 0) {
                0
            } else {
                args.historyId
            }

            val historyModel = HistoryModel(id = historyId, title = title, description = description)

            historyDetailViewModel.saveHistory(historyModel)

            findNavController().navigateUp()
        }
        imgBack.setOnClickListener {
            findNavController().navigateUp()
        }
        imgHistory.setOnClickListener {
            showImagePickerOptions()
        }
    }

    private fun textWatcher() = with(binding) {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val title = etTitle.text.toString()
                val description = etDescription.text.toString()
                isModified = title != (historyDetailViewModel.history.value?.title ?: "") ||
                        description != (historyDetailViewModel.history.value?.description ?: "")
                btnSave.visibility = if (title.isNotEmpty() && description.isNotEmpty() && (args.historyId == 0 || isModified)) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        }
        etTitle.addTextChangedListener(textWatcher)
        etDescription.addTextChangedListener(textWatcher)
    }

    private fun showImagePickerOptions() {
        val options = arrayOf("Take Photo", "Choose from Gallery")
        val builder = android.app.AlertDialog.Builder(requireContext())
        builder.setTitle("Select Photo")
        builder.setItems(options) { _, which ->
            when (which) {
                0 -> dispatchTakePictureIntent()
                1 -> dispatchPickPictureIntent()
            }
        }
        builder.show()
    }

    private fun dispatchTakePictureIntent() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.CAMERA), REQUEST_IMAGE_CAPTURE)
        } else {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (takePictureIntent.resolveActivity(requireActivity().packageManager) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    private fun dispatchPickPictureIntent() {
        val pickPhotoIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(pickPhotoIntent, REQUEST_IMAGE_PICK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_IMAGE_CAPTURE -> {
                    val imageBitmap = data?.extras?.get("data") as Bitmap
                    binding.imgHistory.setImageBitmap(imageBitmap)
                }
                REQUEST_IMAGE_PICK -> {
                    val selectedImageUri: Uri? = data?.data
                    binding.imgHistory.setImageURI(selectedImageUri)
                }
            }
        }
    }
}