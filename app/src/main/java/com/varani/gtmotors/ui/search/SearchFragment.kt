package com.varani.gtmotors.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.varani.gtmotors.R

class SearchFragment : Fragment() {

    private lateinit var carMakeModelButton: TextView
    private lateinit var carYearButton: TextView
    private lateinit var searchButton: Button

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
    }

    private fun bindViews(view: View) {
        carMakeModelButton = view.findViewById(R.id.car_make_model_button)
        carYearButton = view.findViewById(R.id.car_year_button)
        searchButton = view.findViewById(R.id.search_button)

        carMakeModelButton.setOnClickListener {
            findNavController().navigate(R.id.action_searchFragment_to_searchFilterFragment)
        }
        carYearButton.setOnClickListener {
            findNavController().navigate(R.id.action_searchFragment_to_searchFilterFragment)
        }
        searchButton.setOnClickListener {
            findNavController().navigate(R.id.action_search_fragment_to_searchResultsFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title = "Search"
    }
}