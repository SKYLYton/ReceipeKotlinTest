package com.recipes.fragments.search_recipe

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.aymarja.adapters.goods.RecipesAdapter
import com.receipe.App
import com.receipe.databinding.FragmentSearchRecipeBinding
import com.receipe.fragments.search_recipe.model.ResultSearchRecipe
import javax.inject.Inject

class SearchRecipeFragment : Fragment() {

    private var _binding: FragmentSearchRecipeBinding? = null
    private val binding: FragmentSearchRecipeBinding get() = _binding!!
    @Inject
    lateinit var viewModel: SearchViewModel

    private val adapter = RecipesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSearchRecipeBinding.inflate(inflater, container, false)

        _binding = binding

        val searchRecipeComponent = App.instance.applicationComponent.getSearchRecipeComponentFactory().create()
        searchRecipeComponent.inject(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.liveData.observe(viewLifecycleOwner, Observer { renderData(it) })

        viewModel.start()

        initControls()

        viewModel.getSavedRecipes()
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
                if(appState.model is ResultSearchRecipe) {
                    adapter.setList(appState.model.recipes)
                }
            }
            is AppState.Loading -> {

            }
            is AppState.InvalidData -> {
            }
            is AppState.Failure -> {
            }
            is AppState.Error -> {
                appState.code
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        adapter.removeListener()
    }

}