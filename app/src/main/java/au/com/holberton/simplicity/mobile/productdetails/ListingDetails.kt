package au.com.holberton.simplicity.mobile.productdetails

import au.com.holberton.simplicity.mobile.productlisting.DateSerializer
import kotlinx.serialization.Serializable
import java.util.Date

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
    @Serializable(with = DateSerializer::class)
    val createdAt: Date? = null,
    @Serializable(with = DateSerializer::class)
    val updatedAt: Date? = null,
    val __v: Int? = null,
)
