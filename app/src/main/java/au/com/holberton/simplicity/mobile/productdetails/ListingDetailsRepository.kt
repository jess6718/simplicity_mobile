package au.com.holberton.simplicity.mobile.productlisting

import au.com.holberton.simplicity.mobile.productdetails.ListingDetails

object ListingDetailsRepository {

    fun getMockListingDetails(id: String): ListingDetails {
        return ListingDetails(
            productCode = "041412",
            price = "\$100.00",
            imageUrl = "https://c1-ebgames.eb-cdn.com.au/merchandising/images/packshots/651ffac073d643a9acfe0ad022cb88b8_Large.jpg",
            description = "The Pikachu plush toy",
            url = "https://en.wikipedia.org/wiki/Pikachu",
            quantity =67,
            productName = "Pikachu",
            location = "SC1"
        )
    }

    // TODO task 4.2: Fetch data for listing details screen
}
