package com.varani.gtmotors.domain.search

import com.varani.gtmotors.ui.searchresults.SearchResultsModel
import com.varani.gtmotors.ui.searchresults.VehicleModel

data class SearchResponseDomain(
    val vehicleList: List<VehicleDomain>
) {
    fun toModel(): SearchResultsModel {
        return SearchResultsModel(
            this.vehicleList.map {
                it.toModel()
            }
        )
    }
}

data class VehicleDomain(
    val id: String,
    val name: String,
    val title: String,
    val make: String,
    val model: String,
    val year: String,
    val price: String,
) {
    fun toModel(): VehicleModel {
        return VehicleModel(
            name = this.name,
            title = this.title,
            year = this.year,
            price = this.price,
            make = this.make,
            model = this.model,
        )
    }
}