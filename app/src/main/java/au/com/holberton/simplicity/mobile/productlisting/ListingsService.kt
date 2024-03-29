package au.com.holberton.simplicity.mobile.productlisting

import au.com.holberton.simplicity.mobile.BuildConfig
import au.com.holberton.simplicity.mobile.productdetails.ListingDetails
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import retrofit2.Response
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

    @GET("api/image/id/{id}")
    suspend fun getItemImageById(@Path("id") id: String): Response<ResponseBody>
}

// No need to change below
object ListingApi {
    val service: ListingsService = buildRetrofit().create(ListingsService::class.java)
    @OptIn(ExperimentalSerializationApi::class)
    private fun buildRetrofit(): Retrofit {

        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }
}
