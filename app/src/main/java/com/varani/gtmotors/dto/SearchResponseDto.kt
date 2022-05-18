package com.varani.gtmotors.dto

import com.google.gson.annotations.SerializedName
import com.varani.gtmotors.domain.search.SearchResponseDomain
import com.varani.gtmotors.domain.search.VehicleDomain

data class SearchResponseDto(
    @SerializedName("searchResults")
    val searchResults: List<VehicleDto>
) {
    fun toDomain(): SearchResponseDomain {
        return SearchResponseDomain(
            this.searchResults.map {
                it.toDomain()
            }
        )
    }
}

data class VehicleDto(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("title") val title: String,
    @SerializedName("make") val make: String,
    @SerializedName("model") val model: String,
    @SerializedName("year") val year: String,
    @SerializedName("price") val price: String,
) {
    fun toDomain(): VehicleDomain {
        return VehicleDomain(
            id = this.id,
            name = name,
            title = title,
            make = make,
            model = model,
            year = year,
            price = price,
        )
    }
}