package com.varani.gtmotors.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.varani.gtmotors.R

class SearchFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val carMakeModelButton = view.findViewById<TextView>(R.id.car_make_model_button)
        carMakeModelButton.setOnClickListener {
            findNavController().navigate(R.id.action_searchFragment_to_searchFilterFragment)
        }

        val carAgeButton = view.findViewById<TextView>(R.id.car_year_button)
        carAgeButton.setOnClickListener {
            findNavController().navigate(R.id.action_searchFragment_to_searchFilterFragment)
        }

        val searchButton = view.findViewById<TextView>(R.id.search_button)
        searchButton.setOnClickListener {
            findNavController().navigate(R.id.action_search_fragment_to_searchResultsFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title = "Search"
    }
}