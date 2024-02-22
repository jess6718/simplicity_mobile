package au.com.holberton.simplicity.mobile.productlisting

import au.com.holberton.simplicity.mobile.productdetails.ListingDetails
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ListingsService {
    @GET("api/item")
    suspend fun getListings(): List<ListingSummary>

    @GET("api/item/upc/{upc}")
    suspend fun getListingDetails(@Path("upc") upc: Long): ListingDetails

    @PUT("api/item/upc/{upc}/quantity/{quantity}")
    suspend fun updateQuantity(@Path("upc") upc: Long, @Path("quantity") quantity: Int?): Unit
}

// No need to change below
object ListingApi {
    val service: ListingsService = buildRetrofit().create(ListingsService::class.java)
    @OptIn(ExperimentalSerializationApi::class)
    private fun buildRetrofit(): Retrofit {
        //val baseUrl = "https://virtserver.swaggerhub.com/UCSANTOS/Simplicity/1.0.0/" //issue with deployed IP
        val baseUrl = "http://192.168.1.100:3030/"
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }
}
