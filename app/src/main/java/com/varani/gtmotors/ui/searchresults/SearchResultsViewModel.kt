package com.varani.gtmotors.ui.searchresults

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varani.gtmotors.api.NetworkResult
import com.varani.gtmotors.domain.search.Repository
import com.varani.gtmotors.domain.search.SearchRequestDomain
import com.varani.gtmotors.ui.search.SearchViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchResultsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    sealed class UiState {
        class Loaded(val result: SearchResultsModel) : UiState()
        object Loading : UiState()
        class Error(val message: String) : UiState()
    }

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState>
        get() = _uiState

    fun init(state: SearchViewModel.FilterState?) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            val result = repository.searchVehicles(
                SearchRequestDomain(
                    make = state?.make.orEmpty(),
                    model = state?.model.orEmpty(),
                    year = state?.year.orEmpty()
                )
            )
            when (result) {
                is NetworkResult.Success -> {
                    _uiState.value = UiState.Loaded(result.value.toModel())
                }
                is NetworkResult.Failure -> {
                    _uiState.value = UiState.Error(result.throwable.message.orEmpty())
                }
            }
        }
    }
}