package com.varani.gtmotors.domain.search

import com.varani.gtmotors.api.NetworkResult
import com.varani.gtmotors.api.VehicleApi
import com.varani.gtmotors.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val api: VehicleApi,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : Repository {

    override suspend fun searchVehicles(
        searchRequestDomain: SearchRequestDomain
    ): NetworkResult<SearchResponseDomain> {
        return withContext(dispatcher) {
            try {
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
}