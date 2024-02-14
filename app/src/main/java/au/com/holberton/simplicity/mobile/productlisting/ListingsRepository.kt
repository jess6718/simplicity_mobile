package au.com.holberton.simplicity.mobile.productlisting


object ListingsRepository {
//    fun getMockListings(): List<ListingSummary> {
//        return listOf(
//            ListingSummary(
//                "yvg5h",
//                "041412",
//                "\$100.00",
//                "https://c1-ebgames.eb-cdn.com.au/merchandising/images/packshots/651ffac073d643a9acfe0ad022cb88b8_Large.jpg",
//                67,
//                "Pikachu"
//            ),
//            ListingSummary(
//                "agocl",
//                "AG011AQA",
//                "\$96.50",
//                "https://cdn11.bigcommerce.com/s-cf7jv97qb3/images/stencil/1280x1280/products/19498/137828/20203507_2__45330.1697493534.jpg",
//                34,
//                "Charmander"
//            ),
//            ListingSummary(
//                "1bfp0l",
//                "CG011AQA",
//                "\$56.80",
//                "https://i.ebayimg.com/images/g/shsAAOSw6HJZwCzd/s-l1600.jpg",
//                45,
//                "Vaporeon"
//
//            ),
//            ListingSummary(
//                "yvg5h",
//                "CG011AQA",
//                "\$135.00",
//                "https://image.uniqlo.com/UQ/ST3/WesternCommon/imagesgoods/462554/item/goods_54_462554.jpg?width=750",
//                45,
//                "Doraemon"
//            ),
//            ListingSummary(
//                "agocl",
//                "DG011AQA",
//                "\$120.00",
//                "https://pokevault.com/image/cache/catalog/201707/1623925455_leafeon-eevee-collection-2021-plush-toy-3-500x500.jpg",
//                46,
//                "Leafeon"
//            ),
//            ListingSummary(
//                "1bfp0l",
//                "EG011AQA",
//                "\$88.80",
//                "https://c1-ebgames.eb-cdn.com.au/merchandising/images/packshots/54efb0f7bba54de29d59e2da05199b9d_Large.jpg",
//                65,
//                "Bulbasaur"
//            ),
//            ListingSummary(
//                "1bfp0l",
//                "HG011AQA",
//                "\$91.00",
//                "https://i.ebayimg.com/images/g/2ZMAAOSwfMxivUzH/s-l1600.jpg",
//                3,
//                "Flareon"
//            )
//        )
//    }

//    suspend fun getListings(): List<ListingSummary> {
//        return ListingApi.service.getListings().listings
//    }
    suspend fun getListings(): List<ListingSummary> {
        return ListingApi.service.getListings()
    }

    // TODO task 4.2: Fetch data for listing details screen
}
