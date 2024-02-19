package au.com.holberton.simplicity.mobile.productlisting

import au.com.holberton.simplicity.mobile.productdetails.ListingDetails

object ListingDetailsRepository {
    suspend fun getListingDetails(upc: Double): ListingDetails {
        return ListingApi.service.getListingDetails(upc)
    }

    suspend fun updateItemQty(upc: Double, quantity: Int?): Unit {
        ListingApi.service.updateQuantity(upc, quantity)
    }
}
