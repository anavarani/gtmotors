package com.varani.gtmotors.domain.search

import com.varani.gtmotors.dto.SearchResultsDto

data class SearchResultsDomain(
    val id: String,
    val name: String,
    val title: String,
    val make: String,
    val model: String,
    val year: String,
    val price: String,
)

fun SearchResultsDto.toDomain(): SearchResultsDomain {
    return SearchResultsDomain(
        id = this.id,
        name = name,
        title = title,
        make = make,
        model = model,
        year = year,
        price = price,
    )
}