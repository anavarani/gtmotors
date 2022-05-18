package com.varani.gtmotors.ui.searchresults

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.varani.gtmotors.R

data class SearchResultsModel(
    val resultsList: List<VehicleModel>
)

data class VehicleModel(
    val name: String,
    val title: String,
    val price: String,
    val make: String,
    val model: String,
    val year: String,
)

class SearchResultsAdapter(
    private var listingItems: List<VehicleModel> = emptyList()
) : RecyclerView.Adapter<SearchResultsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_results_row_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(holder, position)
    }

    override fun getItemCount() = listingItems.size

    fun setListings(items: List<VehicleModel>) {
        listingItems = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {
        private val name: TextView = view.findViewById(R.id.listing_item_name)
        private val title: TextView = view.findViewById(R.id.listing_item_title)
        private val price: TextView = view.findViewById(R.id.listing_item_price)
        private val make: TextView = view.findViewById(R.id.listing_item_make)
        private val model: TextView = view.findViewById(R.id.listing_item_model)
        private val year: TextView = view.findViewById(R.id.listing_item_year)

        fun bind(holder: ViewHolder, position: Int) {
            val item = listingItems[position]
            name.text = item.name
            title.text = item.title
            price.text = item.price

            make.text = if (item.make.isNotEmpty()) {
                holder.itemView.context.getString(R.string.vehicle_make, item.make)
            } else {
                item.make
            }
            model.text = if (item.model.isNotEmpty()) {
                holder.itemView.context.getString(R.string.vehicle_model, item.model)
            } else {
                item.model
            }
            year.text = if (item.year.isNotEmpty()) {
                holder.itemView.context.getString(R.string.vehicle_year, item.year)
            } else {
                item.year
            }
        }
    }
}