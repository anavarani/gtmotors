package com.varani.gtmotors.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.varani.gtmotors.R
import com.varani.gtmotors.ui.searchfilter.Filter
import com.varani.gtmotors.utils.show

class SearchFragment : Fragment() {

    private lateinit var carMakeModelButton: TextView
    private lateinit var carMakeModelCheck: ImageView
    private lateinit var carYearButton: TextView
    private lateinit var carYearCheck: ImageView
    private lateinit var searchButton: Button

    private val searchViewModel: SearchViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
        observeLiveData()
    }

    private fun bindViews(view: View) {
        carMakeModelButton = view.findViewById(R.id.car_make_model_button)
        carMakeModelCheck = view.findViewById(R.id.car_make_model_check)
        carYearButton = view.findViewById(R.id.car_year_button)
        carYearCheck = view.findViewById(R.id.car_year_check)
        searchButton = view.findViewById(R.id.search_button)

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