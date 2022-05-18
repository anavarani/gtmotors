package com.varani.gtmotors.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.varani.gtmotors.MotorsApplication
import com.varani.gtmotors.R
import com.varani.gtmotors.ui.searchfilter.Filter
import com.varani.gtmotors.utils.show
import javax.inject.Inject

class SearchFragment : Fragment() {

    private lateinit var carMakeModelButton: TextView
    private lateinit var carMakeModelCheck: ImageView
    private lateinit var carYearButton: TextView
    private lateinit var carYearCheck: ImageView
    private lateinit var searchButton: Button
    private lateinit var clearFilterButton: TextView

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var searchViewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createViewModel()
        bindViews(view)
        observeLiveData()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MotorsApplication).appComponent.inject(this)
    }

    private fun createViewModel() {
        searchViewModel = ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)
    }

    private fun bindViews(view: View) {
        carMakeModelButton = view.findViewById(R.id.car_make_model_button)
        carMakeModelCheck = view.findViewById(R.id.car_make_model_check)
        carYearButton = view.findViewById(R.id.car_year_button)
        carYearCheck = view.findViewById(R.id.car_year_check)
        searchButton = view.findViewById(R.id.search_button)
        clearFilterButton = view.findViewById(R.id.clear_filter)

        carMakeModelButton.setOnClickListener {
            val action = SearchFragmentDirections.goToFilterFragment(Filter.MAKE_MODEL)
            findNavController().navigate(action)
        }
        carYearButton.setOnClickListener {
            val action = SearchFragmentDirections.goToFilterFragment(Filter.YEAR)
            findNavController().navigate(action)
        }
        searchButton.setOnClickListener {
            findNavController().navigate(R.id.goToSearchResultsFragment)
        }
        clearFilterButton.setOnClickListener {
            searchViewModel.reset()
        }
    }

    private fun observeLiveData() {
        searchViewModel.apply {
            searchFilterState.observe(
                requireActivity(),
                Observer {
                    onFilterUpdated(it)
                }
            )
        }
    }

    private fun onFilterUpdated(it: SearchViewModel.FilterState) {
        carMakeModelCheck.show(it.make.isNotEmpty() || it.model.isNotEmpty())
        carYearCheck.show(it.year.isNotEmpty())
    }
}