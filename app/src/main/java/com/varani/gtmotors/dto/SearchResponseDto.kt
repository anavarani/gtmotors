package com.varani.gtmotors.dto

import com.google.gson.annotations.SerializedName

data class SearchResponseDto(
    @SerializedName("searchResults")
    val searchResults: List<SearchResultsDto>
)

data class SearchResultsDto(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("title") val title: String,
    @SerializedName("make") val make: String,
    @SerializedName("model") val model: String,
    @SerializedName("year") val year: String,
    @SerializedName("price") val price: String,
)