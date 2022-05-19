package com.varani.gtmotors.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchViewModel @Inject constructor() : ViewModel() {

    private val _searchFilterState = MutableLiveData<FilterState>()
    val searchFilterState: LiveData<FilterState> = _searchFilterState

    data class FilterState(
        val make: String = "",
        val model: String = "",
        val year: String = "",
    )

    fun setYear(year: String) {
        val newState = searchFilterState.value?.copy(year = year)
            ?: FilterState(year = year)
        _searchFilterState.value = newState
    }

    fun setMakeModel(make: String, model: String) {
        val newState = searchFilterState.value?.copy(make = make, model = model)
            ?: FilterState(make = make, model = model)
        _searchFilterState.value = newState
    }

    fun reset() {
        _searchFilterState.value = FilterState()
    }
}