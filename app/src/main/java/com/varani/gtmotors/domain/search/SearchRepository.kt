package com.varani.gtmotors.domain.search

import com.varani.gtmotors.api.NetworkResult
import com.varani.gtmotors.api.VehicleApi
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private var api: VehicleApi
) : Repository {

    override suspend fun searchVehicles(
        searchRequestDomain: SearchRequestDomain
    ): NetworkResult<SearchResponseDomain> {
        return try {
            val results = api.searchVehicles(
                make = searchRequestDomain.make,
                model = searchRequestDomain.model,
                year = searchRequestDomain.year,
            ).toDomain()
            NetworkResult.Success(results)
        } catch (throwable: Throwable) {
            NetworkResult.Failure(throwable)
        }
    }
}