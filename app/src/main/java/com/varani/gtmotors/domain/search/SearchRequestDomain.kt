package com.varani.gtmotors.domain.search

data class SearchRequestDomain(
    val make: String,
    val model: String,
    val year: String,
)