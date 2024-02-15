package au.com.holberton.simplicity.mobile.productlisting

import kotlinx.serialization.Serializable

// TODO task 4.1: Fetch data for listings screen
@Serializable
data class ListingSummary(
    val _id: String,
    val name: String,
    val category: String,
    val quantity: Double,
    val upc: Double,
    val costPrice: Double,
    val salePrice: Double,
    val location: String
)