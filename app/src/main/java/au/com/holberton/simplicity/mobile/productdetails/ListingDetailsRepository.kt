package au.com.holberton.simplicity.mobile.productlisting

import au.com.holberton.simplicity.mobile.productdetails.ListingDetails

object ListingDetailsRepository {
    suspend fun getListingDetails(upc: Double): ListingDetails {
        return ListingApi.service.getListingDetails(upc)
    }
}
