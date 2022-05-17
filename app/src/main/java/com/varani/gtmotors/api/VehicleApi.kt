package com.varani.gtmotors.api

import com.varani.gtmotors.dto.SearchResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface VehicleApi {

    @GET(SEARCH)
    suspend fun searchVehicles(
        @Query("make") make: String,
        @Query("model") model: String,
        @Query("year") year: String,
    ): SearchResponseDto

    companion object {
        private const val SEARCH = "search"
    }
}