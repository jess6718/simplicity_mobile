package au.com.holberton.simplicity.mobile.productdetails

import kotlinx.serialization.Serializable

// TODO task 4.2: Fetch data for listing details screen
@Serializable
data class ListingDetails(
    val _id: String,
    val name: String,
    val category: String,
    val quantity: Int?,
    val upc: Double,
    val costPrice: Double,
    val salePrice: Double,
    val location: String,
)
