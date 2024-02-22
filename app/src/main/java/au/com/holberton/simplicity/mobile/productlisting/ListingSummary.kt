package au.com.holberton.simplicity.mobile.productlisting

import kotlinx.serialization.Serializable
import java.util.Date

// TODO task 4.1: Fetch data for listings screen
@Serializable
data class ListingSummary(
    val _id: String,
    val name: String,
    val category: String,
    val quantity: Int?,
    val upc: Long,
    val costPrice: Double,
    val salePrice: Double,
    val location: String,
    @Serializable(with = DateSerializer::class)
    val createdAt: Date? = null,
    @Serializable(with = DateSerializer::class)
    val updatedAt: Date? = null,
    val __v: Int? = null,
)