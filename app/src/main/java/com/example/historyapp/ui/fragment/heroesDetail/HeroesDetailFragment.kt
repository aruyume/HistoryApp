package com.example.historyapp.ui.fragment.heroesDetail

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.historyapp.data.model.HeroModel
import com.example.historyapp.databinding.FragmentHeroesDetailBinding
import com.example.historyapp.ui.fragment.base.fragments.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HeroesDetailFragment : BaseFragment<FragmentHeroesDetailBinding, HeroesDetailViewModel>(
    FragmentHeroesDetailBinding::inflate
) {
    private val args: HeroesDetailFragmentArgs by navArgs()
    private val heroesDetailViewModel: HeroesDetailViewModel by viewModel()
    private var isModified = false

    override val viewModel: HeroesDetailViewModel
        get() = heroesDetailViewModel

    override fun setupObservers() = with(binding) {
        super.setupObservers()
        viewModel.loadHistory(args.heroesId)
        viewModel.heroes.observe(viewLifecycleOwner) { heroes ->
            heroes?.let {
                etName.setText(it.name)
                etSurname.setText(it.surname)
                isModified = it.name != viewModel.heroes.value?.name ||
                        it.surname != viewModel.heroes.value?.surname
                btnSave.visibility =
                    if (args.heroesId == 0 || isModified) View.VISIBLE else View.GONE
            }
        }
    }

    override fun setupClickListeners() = with(binding) {
        super.setupClickListeners()
        btnSave.setOnClickListener {
            val name = etName.text.toString()
            val surname = etSurname.text.toString()
            val heroesId = if (args.heroesId == 0) 0 else args.heroesId

            val heroModel = HeroModel(
                id = heroesId,
                name = name,
                surname = surname
            )
            viewModel.saveHistory(heroModel)
            findNavController().navigateUp()
        }
        imgBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun initViews() = with(binding) {
        super.initViews()
        etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val name = etName.text.toString()
                val surname = etSurname.text.toString()
                isModified = name != (viewModel.heroes.value?.name ?: "") ||
                        surname != (viewModel.heroes.value?.surname ?: "")
                btnSave.visibility =
                    if (name.isNotEmpty() && surname.isNotEmpty() && (args.heroesId == 0 || isModified)) {
                        View.VISIBLE
                    } else {
                        View.GONE
                    }
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        etSurname.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val name = etName.text.toString()
                val surname = etSurname.text.toString()
                isModified = name != (viewModel.heroes.value?.name ?: "") ||
                        surname != (viewModel.heroes.value?.surname ?: "")
                btnSave.visibility =
                    if (name.isNotEmpty() && surname.isNotEmpty() && (args.heroesId == 0 || isModified)) {
                        View.VISIBLE
                    } else {
                        View.GONE
                    }
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }
}