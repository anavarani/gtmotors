package com.varani.gtmotors.ui.searchresults

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.varani.gtmotors.MotorsApplication
import com.varani.gtmotors.R
import com.varani.gtmotors.ui.search.SearchViewModel
import com.varani.gtmotors.utils.TAG
import com.varani.gtmotors.utils.show
import javax.inject.Inject


class SearchResultsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var searchViewModel: SearchViewModel
    lateinit var searchResultsViewModel: SearchResultsViewModel

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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MotorsApplication).appComponent.inject(this)
    }

    private fun loadData() {
        searchResultsViewModel.init(searchViewModel.searchFilterState.value)
    }

    private fun createViewModel() {
        searchViewModel = ViewModelProvider(this, viewModelFactory)[SearchViewModel::class.java]
        searchResultsViewModel =
            ViewModelProvider(this, viewModelFactory)[SearchResultsViewModel::class.java]
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
                    is SearchResultsViewModel.UiState.Loading -> showLoadingDialog()
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

    private fun showLoadingDialog(show: Boolean = true) {
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