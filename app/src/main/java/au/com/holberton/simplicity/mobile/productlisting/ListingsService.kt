package au.com.holberton.simplicity.mobile.productlisting

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET

interface ListingsService {
    @GET("api/item")
    suspend fun getListings(): List<ListingSummary>

    // TODO task 4.2: Fetch data for listing details screen
}

// No need to change below
object ListingApi {
    val service: ListingsService = buildRetrofit().create(ListingsService::class.java)

    @OptIn(ExperimentalSerializationApi::class)
    private fun buildRetrofit(): Retrofit {
        val baseUrl = "http://localhost:3030/"
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }
}
