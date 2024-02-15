package au.com.holberton.simplicity.mobile.productlisting

import au.com.holberton.simplicity.mobile.productdetails.ListingDetails

object ListingDetailsRepository {
    suspend fun getListingDetails(id: String): ListingDetails {
        return ListingApi.service.getListingDetails(id)
    }
}
