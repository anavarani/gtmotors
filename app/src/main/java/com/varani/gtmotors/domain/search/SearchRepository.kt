package com.varani.gtmotors.domain.search

import com.varani.gtmotors.api.NetworkResult
import com.varani.gtmotors.api.VehicleApi
import com.varani.gtmotors.domain.RetrofitClient

class SearchRepository {

    private var api: VehicleApi = RetrofitClient.vehicleApi

    suspend fun searchVehicles(
        searchRequestDomain: SearchRequestDomain
    ): NetworkResult<List<SearchResultsDomain>> {
        return try {
            val results = api.searchVehicles(
                make = searchRequestDomain.make,
                model = searchRequestDomain.model,
                year = searchRequestDomain.year,
            ).searchResults.map {
                it.toDomain()
            }
            NetworkResult.Success(results)
        } catch (throwable: Throwable) {
            NetworkResult.Failure(throwable)
        }
    }
}