package au.com.holberton.simplicity.mobile

// TODO task 4.1: Fetch data for listings screen
data class ListingSummary(
    val id: String,
    val productCode: String,
    val price: String,
    val imageUrl: String,
    val quantity: Int,
    val productName: String
)

data class ListingsResponse(val listings: List<ListingSummary>)
