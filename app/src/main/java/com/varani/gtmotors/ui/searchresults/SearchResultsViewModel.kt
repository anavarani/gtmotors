package com.varani.gtmotors.ui.searchresults

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varani.gtmotors.api.NetworkResult
import com.varani.gtmotors.domain.search.SearchRepository
import com.varani.gtmotors.domain.search.SearchRequestDomain
import com.varani.gtmotors.ui.search.SearchViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchResultsViewModel @Inject constructor(
    private val repository: SearchRepository
) : ViewModel() {

    sealed class UiState {
        class Loaded(val result: SearchResultsModel) : UiState()
        class Loading(val show: Boolean = true) : UiState()
        class Error(val message: String) : UiState()
    }

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState>
        get() = _uiState


    fun loadState(state: SearchViewModel.FilterState?) {
        state?.let {
            viewModelScope.launch {
                _uiState.value = UiState.Loading()
                repository.searchVehicles(
                    SearchRequestDomain(
                        make = state.make,
                        model = state.model,
                        year = state.year
                    )
                ).also { response ->
                    when (response) {
                        is NetworkResult.Success -> {
                            _uiState.value = UiState.Loaded(response.value.toModel())
                        }
                        is NetworkResult.Failure -> {
                            _uiState.value = UiState.Error(response.throwable.message.orEmpty())
                        }
                    }
                }
            }
        }
    }
}