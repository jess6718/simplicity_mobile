package au.com.holberton.simplicity.mobile.productlisting

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.Typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import au.com.holberton.simplicity.mobile.common.CommonComponent
import au.com.holberton.simplicity.mobile.common.ExceptionHandler
import au.com.holberton.simplicity.mobile.ui.theme.WorkshopTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun ListingsScreen(navigate: (String) -> Unit, onBackPressed: () -> Unit) {
    val searchTerm = remember { mutableStateOf("") }
    val isSortedByProductCode = remember { mutableStateOf(false) }
    val listings = remember { mutableStateOf(emptyList<ListingSummary>()) }
    val snackbarHostState = remember { SnackbarHostState() }
    val typography = Typography(
        defaultFontFamily = FontFamily.SansSerif // You can set your desired font family here
    )

    fun getFilteredListings(): List<ListingSummary> {
        val filteredListings = listings.value
            .filter {
                it.name.contains(
                    searchTerm.value,
                    ignoreCase = true
                ) || it.upc.toString().contains(
                    searchTerm.value,
                    ignoreCase = true
                )
            }
        //If search term empty return empty list
        return if (searchTerm.value.isEmpty()) {
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
        try {
            listings.value = ListingsRepository.getListings()
        } catch (error: Exception) {
            ExceptionHandler.exceptionHandler(error, snackbarHostState)
        }

    }
    MaterialTheme(
        typography = typography
    ) {
        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Text (
                        text = "Products Search",
                        style = MaterialTheme.typography.h6
                    )
                },
                    navigationIcon = {
                        IconButton(onClick = onBackPressed) {
                            Icon(
                                Icons.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    })
            },
            snackbarHost = { CommonComponent.TopSnackbarHost(snackbarHostState) }
        ) { padding ->
            Column(modifier = Modifier.padding(padding)) {
                SearchBar(searchTerm = searchTerm.value, onValueChange = { searchTerm.value = it })
                SortingBar(
                    isSortedByProductCode = isSortedByProductCode.value,
                    toggleSorting = { isSortedByProductCode.value = !isSortedByProductCode.value }
                )
                if (searchTerm.value.isNotEmpty()) {
                    Header() // Add header for listed products
                }

                LazyColumn {
                    items(getFilteredListings()) {
                        ListingCard(it, navigate)
                    }
                }
            }
        }
    }
}

@Composable
private fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween // Align items to the start (left)

    ) {
        Text(
            text = "Image",
            color = Color(0xFF444444),
            modifier = Modifier.weight(0.3f)
        )
        Text(text = "UPC", color = Color(0xFF444444), modifier = Modifier.weight(0.55f))
        Text(text = "Name", color = Color(0xFF444444), modifier = Modifier.weight(0.3f))
        Text(text = "Qty", color = Color(0xFF444444), modifier = Modifier.weight(0.2f))
        Text(text = "Price", color = Color(0xFF444444), modifier = Modifier.weight(0.2f))
    }
}

@Composable
private fun ListingCard(
    listingSummary: ListingSummary,
    navigate: (String) -> Unit
) {
    val upcString = listingSummary.upc.toString()
    // Define a mutable state to hold the image bitmap
    val bitmapState = remember { mutableStateOf<Bitmap?>(null) }

    // Fetch the image bitmap asynchronously
    LaunchedEffect(key1 = listingSummary._id) {
        withContext(Dispatchers.IO) {
            try {
                val bitmap = ListingsRepository.getItemImageById(listingSummary._id)
                bitmapState.value = bitmap
            } catch (e: Exception) {
                Log.e("ListingCard", "Error fetching image: ${e.message}")
            }
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { navigate("listingDetails/${listingSummary.upc}") },
        elevation = 2.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Display the image bitmap if available
            bitmapState.value?.let { bitmap ->
                Box(
                    modifier = Modifier.weight(0.3f))
                    {
                    Image(
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = "Listing image",
                        modifier = Modifier
                            .height(65.dp)
                            .width(65.dp),
                        contentScale = ContentScale.FillBounds
                    )
                }
            }
            Column(modifier = Modifier.weight(0.55f)) {
                Text(
                    text = upcString,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.secondary,
                )
            }
            Column(modifier = Modifier.weight(0.3f)) {
                Text(
                    text = listingSummary.name,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.secondary
                )
            }
            Column(modifier = Modifier.weight(0.2f)) {
                Text(
                    text = listingSummary.quantity.toString(),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.secondary
                )
            }
            Column(modifier = Modifier.weight(0.2f)) {
                Text(
                    text = listingSummary.salePrice.toString(),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.secondary
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
            Text("Search by UPC or Name")
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
