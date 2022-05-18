package com.varani.gtmotors.domain.search

import com.varani.gtmotors.api.NetworkResult

interface Repository {
    suspend fun searchVehicles(searchRequestDomain: SearchRequestDomain): NetworkResult<SearchResponseDomain>
}