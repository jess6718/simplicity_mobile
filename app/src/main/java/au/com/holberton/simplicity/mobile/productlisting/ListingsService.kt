package au.com.holberton.simplicity.mobile.productlisting

import au.com.holberton.simplicity.mobile.productdetails.ListingDetails
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

interface ListingsService {
    @GET("api/item")
    suspend fun getListings(): List<ListingSummary>

    @GET("api/item/id/{id}")
    suspend fun getListingDetails(@Path("id") id: String): ListingDetails
}

// No need to change below
object ListingApi {
    val service: ListingsService = buildRetrofit().create(ListingsService::class.java)
    @OptIn(ExperimentalSerializationApi::class)
    private fun buildRetrofit(): Retrofit {
        val baseUrl = "http://10.0.2.2:3030/"
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }
}
