package au.com.holberton.simplicity.mobile.productdetails

// TODO task 4.2: Fetch data for listing details screen
data class ListingDetails(
    val productCode: String,
    val price: String,
    val imageUrl: String,
    val description: String?,
    val url: String, //todo del
    val quantity: Int,
    val productName: String,
    val location: String
)
