package com.varani.gtmotors.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {

    private val _searchFilterState = MutableLiveData<FilterState>()
    val searchFilterState: LiveData<FilterState> = _searchFilterState

    data class FilterState(
        val make: String = "",
        val model: String = "",
        val year: String = "",
    )

    init {
        _searchFilterState.value = FilterState()
    }

    fun setYear(year: String) {
        _searchFilterState.value = _searchFilterState.value?.copy(year = year)
    }

    fun setMakeModel(make: String, model: String) {
        _searchFilterState.value = _searchFilterState.value?.copy(make = make, model = model)
    }
}