package com.example.historyapp.ui.fragment.heroes

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.historyapp.data.model.HeroModel
import com.example.historyapp.databinding.FragmentHeroesBinding
import com.example.historyapp.ui.fragment.base.fragments.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HeroesFragment : BaseFragment<FragmentHeroesBinding, HeroesViewModel>(
    FragmentHeroesBinding::inflate
) {

    private val heroesAdapter by lazy {
        HeroesAdapter { hero ->
            onHeroClicked(hero)
        }
    }

    override val viewModel: HeroesViewModel by viewModel()

    override fun setupClickListeners() {
        super.setupClickListeners()
        with(binding) {
            btnAdd.setOnClickListener {
                val action =
                    HeroesFragmentDirections.actionHeroesFragmentToHeroDetailFragment(0)
                findNavController().navigate(action)
            }
            imgClearHeroes.setOnClickListener {
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
        binding.rvHeroes.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = heroesAdapter
        }
    }

    override fun loadData() {
        viewModel.allData.observe(viewLifecycleOwner) { heroesList ->
            heroesAdapter.submitList(heroesList)
        }
    }

    private fun onHeroClicked(hero: HeroModel) {
        val action = HeroesFragmentDirections.actionHeroesFragmentToHeroDetailFragment(hero.id)
        findNavController().navigate(action)
    }
}