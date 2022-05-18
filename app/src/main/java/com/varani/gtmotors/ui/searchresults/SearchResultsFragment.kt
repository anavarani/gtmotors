package com.varani.gtmotors.ui.searchresults

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.varani.gtmotors.R
import com.varani.gtmotors.ui.search.SearchViewModel
import com.varani.gtmotors.utils.TAG
import com.varani.gtmotors.utils.show


class SearchResultsFragment : Fragment() {

    private val searchViewModel: SearchViewModel by activityViewModels()
    private lateinit var searchResultsViewModel: SearchResultsViewModel
    private lateinit var adapter: SearchResultsAdapter
    private lateinit var loadingIndicator: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_results_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createViewModel()
        bindViews(view)
        observeLiveData()
        loadData()
    }

    private fun loadData() {
        searchResultsViewModel.loadState(searchViewModel.searchFilterState.value)
    }

    private fun createViewModel() {
        searchResultsViewModel = ViewModelProvider(this)[SearchResultsViewModel::class.java]
    }

    private fun bindViews(view: View) {
        val recyclerView: RecyclerView = view.findViewById(R.id.search_results_list_view)
        loadingIndicator = view.findViewById(R.id.loading_indicator)
        adapter = SearchResultsAdapter()
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    private fun observeLiveData() {
        searchResultsViewModel.uiState.observe(
            viewLifecycleOwner,
            Observer {
                when (it) {
                    is SearchResultsViewModel.UiState.Loaded -> onLoaded(it.result)
                    is SearchResultsViewModel.UiState.Loading -> showLoadingDialog(it.show)
                    is SearchResultsViewModel.UiState.Error -> onError(it.message)
                }
            }
        )
    }

    private fun onLoaded(model: SearchResultsModel) {
        showLoadingDialog(false)
        adapter.setListings(model.resultsList)
    }

    private fun onError(message: String) {
        showLoadingDialog(false)
        showErrorAndReturn(message)
    }

    private fun showLoadingDialog(show: Boolean) {
        loadingIndicator.show(show)
    }

    private fun showErrorAndReturn(errorMessage: String) {
        Log.e(TAG, errorMessage)
        Toast.makeText(
            requireContext(),
            "Unable to find vehicles. Try again later",
            Toast.LENGTH_SHORT
        ).show()
        findNavController().navigateUp()
    }
}