package au.com.holberton.simplicity.mobile.productlisting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import au.com.holberton.simplicity.mobile.R
import au.com.holberton.simplicity.mobile.ui.theme.WorkshopTheme
import coil.compose.AsyncImage

@Composable
fun ListingsScreen(navigate: (String) -> Unit, onBackPressed: () -> Unit) {
    val searchTerm = remember { mutableStateOf("") }
    val isSortedByProductCode = remember { mutableStateOf(false) }
    val listings = remember { mutableStateOf(emptyList<ListingSummary>()) }

    // TODO task 3: Filter and sort listings
    fun getFilteredListings(): List<ListingSummary> {
        val filteredListings = listings.value
            .filter {
                  it.name.contains(
                     searchTerm.value,
                    ignoreCase = true
                )
            }
        //If search term empty return empty list
        return if (searchTerm.value.isEmpty()){
            emptyList()
        } else {
            if (isSortedByProductCode.value) {
                filteredListings.sortedBy { it.upc }
            } else {
                filteredListings
            }
        }
    }

    // Create an effect that matches the lifecycle of ListingsScreen.
    // If ListingsScreen recomposes, the fetch shouldn't start again.

    LaunchedEffect(true) {
        listings.value = ListingsRepository.getListings()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Products Search") },
            navigationIcon = {
                IconButton(onClick = onBackPressed) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            })
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            SearchBar(searchTerm = searchTerm.value, onValueChange = { searchTerm.value = it })
            SortingBar(
                isSortedByProductCode = isSortedByProductCode.value,
                toggleSorting = { isSortedByProductCode.value = !isSortedByProductCode.value }
            )
            if (searchTerm.value.isNotEmpty()){
                Header() // Add header for listed products
            }

            // TODO task 2: Display multiple listing cards
            LazyColumn{
                    items(getFilteredListings()) {
                        ListingCard(it, navigate)
                    }
            }
        }
    }
}

@Composable
private fun Header() {
    // TODO: Implement the header with image, product code, product name, quantity, and price
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "", modifier = Modifier.weight(1f))
        Text(text = "Code", modifier = Modifier.weight(1f))
        Text(text = "Name", modifier = Modifier.weight(1f))
        Text(text = "Qty", modifier = Modifier.weight(1f))
        Text(text = "Price", modifier = Modifier.weight(1f))
    }
}

@Composable
private fun ListingCard(
    listingSummary: ListingSummary,
    navigate: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { navigate("listingDetails/${listingSummary._id}") },
        elevation = 2.dp
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
//            AsyncImage(
//                model = listingSummary.imageUrl,
//                contentDescription = "Listing image",
//                modifier = Modifier
//                    .height(65.dp)
//                    .width(65.dp),
//                contentScale = ContentScale.FillBounds
//            )
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = listingSummary.upc.toString(),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.primary,
                )
            }
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = listingSummary.name,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.primary
                )
            }
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = listingSummary.quantity.toString(),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.primary
                )
            }
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = listingSummary.salePrice.toString(),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.primary
                )
            }
        }
    }
}


// No need to change below
@Composable
private fun SearchBar(searchTerm: String, onValueChange: (String) -> Unit) {
    TextField(
        value = searchTerm,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text("Search by productCode")
        }
    )
}

@Composable
private fun SortingBar(isSortedByProductCode: Boolean, toggleSorting: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "Sort by:",
            modifier = Modifier.padding(horizontal = 6.dp)
        )
        RadioButton(
            selected = !isSortedByProductCode,
            onClick = toggleSorting
        )
        Text(
            text = "Default",
            modifier = Modifier.clickable(onClick = toggleSorting)
        )
        Spacer(
            modifier = Modifier.size(4.dp)
        )
        RadioButton(
            selected = isSortedByProductCode,
            onClick = toggleSorting
        )
        Text(
            text = "productCode",
            modifier = Modifier.clickable(onClick = toggleSorting)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ListingsScreenPreview() {
    WorkshopTheme {
        ListingsScreen ({ }, { })
    }
}
