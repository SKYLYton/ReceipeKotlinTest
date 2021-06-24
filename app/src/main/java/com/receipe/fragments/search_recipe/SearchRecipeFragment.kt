package com.recipes.fragments.search_recipe

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.aymarja.adapters.goods.RecipesAdapter
import com.receipe.databinding.FragmentSearchRecipeBinding
import com.receipe.fragments.search_recipe.model.ResultSearchRecipe

class SearchRecipeFragment : Fragment() {

    private var _binding: FragmentSearchRecipeBinding? = null
    private val binding: FragmentSearchRecipeBinding get() = _binding!!
    private lateinit var viewModel: SearchViewModel
    private val adapter = RecipesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSearchRecipeBinding.inflate(inflater, container, false)

        _binding = binding

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })

        initControls()

        viewModel.gelSavedRecipes()
    }

    private fun initControls() {

        adapter.onClickItemListener = {
            val action = SearchRecipeFragmentDirections.actionSearchRecipeFragmentToRecipeFragment()
            Navigation.findNavController(binding.root).navigate(action)
        }

        binding.recGoods.adapter = adapter

        binding.buttonHistory.setOnClickListener {
            val action = SearchRecipeFragmentDirections.actionSearchRecipeFragmentToHistoryFragment()
            Navigation.findNavController(binding.root).navigate(action)
        }

        binding.editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isNotEmpty()) {
                    viewModel.search(s.toString())
                } else {
                    adapter.setList(emptyList())
                }
            }

        })
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success<*> -> {
                val recipes = appState.model as ResultSearchRecipe
                adapter.setList(recipes.recipes)
            }
            is AppState.Loading -> {
            }
            is AppState.InvalidData -> {
            }
            is AppState.Failure -> {
            }
            is AppState.Error -> {
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        adapter.removeListener()
    }

}