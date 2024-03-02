package au.com.holberton.simplicity.mobile.productlisting

import android.graphics.Bitmap
import android.graphics.BitmapFactory


object ListingsRepository {
    suspend fun getListings(): List<ListingSummary> {
        return ListingApi.service.getListings()
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
