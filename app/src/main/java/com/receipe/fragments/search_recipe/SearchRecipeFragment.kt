package com.recipes.fragments.search_recipe

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.aymarja.adapters.goods.RecipesAdapter
import com.receipe.databinding.FragmentSearchRecipeBinding
import com.receipe.fragments.search_recipe.adapters.goods.OnClickItemListener
import com.recipes.retrofit.model.recipe.Hit
import com.recipes.retrofit.model.recipe.ResultRecipeModel

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
    }

    private fun initControls() {
        // TODO: сделать через лямбду
        adapter.onClickItemListener = object : OnClickItemListener {
            override fun onClick(orderModel: Hit?, pos: Int) {

            }
        }

        binding.recGoods.adapter = adapter

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
                val recipes = appState.model as ResultRecipeModel
                adapter.setList(recipes.hits)
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