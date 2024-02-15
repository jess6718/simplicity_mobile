package au.com.holberton.simplicity.mobile.productlisting


object ListingsRepository {
    suspend fun getListings(): List<ListingSummary> {
        return ListingApi.service.getListings()
    }
}
