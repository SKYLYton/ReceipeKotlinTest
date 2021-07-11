package com.receipe.fragments.history

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.aymarja.adapters.goods.HistoryAdapter
import com.receipe.App
import com.receipe.databinding.HistoryFragmentBinding
import com.receipe.fragments.history.model.HistoryItem
import com.recipes.fragments.history.AppState
import javax.inject.Inject

class HistoryFragment : Fragment() {

    private var _binding: HistoryFragmentBinding? = null
    private val binding: HistoryFragmentBinding get() = _binding!!
    private val adapter = HistoryAdapter()

    @Inject
    lateinit var viewModel: HistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = HistoryFragmentBinding.inflate(inflater, container, false)

        _binding = binding

        val historyComponent = App.instance.applicationComponent.getHistoryComponentFactory().create()
        historyComponent.inject(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.liveData.observe(viewLifecycleOwner, Observer { renderData(it) })

        viewModel.start()

        viewModel.getHistory()

        initControls()
    }

    private fun initControls() {
        adapter.onClickItemListener = {

        }

        binding.recHistory.adapter = adapter
    }

    private fun renderData(appState: AppState?) {
        when (appState) {
            is AppState.Success<*> -> {
                val history = appState.model as List<HistoryItem>
                adapter.setList(history)
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
    }

}