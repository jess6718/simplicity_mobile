package au.com.holberton.simplicity.mobile.productlisting

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import au.com.holberton.simplicity.mobile.productdetails.ListingDetails

object ListingDetailsRepository {
    suspend fun getListingDetails(upc: Long): ListingDetails {
        return ListingApi.service.getListingDetails(upc)
    }

    suspend fun updateItemQty(upc: Long, quantity: Int?): Unit {
        ListingApi.service.updateQuantity(upc, quantity)
    }

    suspend fun getItemImageById(id: String): Bitmap? {
        val response = ListingApi.service.getItemImageById(id)
        return if (response.isSuccessful) {
            // Read the response body as InputStream and convert it to Bitmap
            BitmapFactory.decodeStream(response.body()?.byteStream())
        } else {
            null // Handle error case appropriately
        }
    }
}
