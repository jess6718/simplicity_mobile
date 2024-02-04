package au.com.holberton.simplicity.mobile.productdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import au.com.holberton.simplicity.mobile.productlisting.ListingDetailsRepository
import au.com.holberton.simplicity.mobile.productlisting.ListingsRepository
import au.com.holberton.simplicity.mobile.ui.theme.WorkshopTheme

@Composable
fun ListingDetailsScreen(onBackPressed: () -> Unit, listingId: String?) {
    val listingDetails = remember { mutableStateOf<ListingDetails?>(null) }

    // TODO task 4.2: Fetch data for listing details screen
    listingId?.let {
        listingDetails.value = ListingDetailsRepository.getMockListingDetails(it)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Item Information") },
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
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            listingDetails?.value?.let {
                ListingDetailsView(it)
            }
        }
    }
}

// TODO bonus challenge: Complete Listing Details Screen
@Composable
private fun ListingDetailsView(listingDetails: ListingDetails) {
    /* val isBookmarked = remember {
        mutableStateOf(false)
    } */
    val quantity = remember { mutableStateOf(listingDetails.quantity) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding()
        ) {
            // Display product name and product code in the same line
            Text(
                text = "Name: ${listingDetails.productName}",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.primary,
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 10.dp)
            )
            Text(
                text = "Code: ${listingDetails.productCode}",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.primary,
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 10.dp)
            )
        }

        // Display price on a new line
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding()
        ) {
            Text(
                text = "Price: ${listingDetails.price}",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.padding(vertical = 10.dp)
            )
        }

        // Display location on a new line
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding()
        ) {
            Text(
                text = "Location: ${listingDetails.location}",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.padding(vertical = 10.dp)
            )
        }

        // Display description on a new line
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding()
        ) {
            Text(
                text = "Description: ${listingDetails.description}",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.padding(vertical = 10.dp)
            )
        }
        // Display Qty on a new line
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Quantity: ",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.primary,
                modifier = Modifier
                    .padding()
            )
            OutlinedTextField(
                value = quantity.value.toString(),
                onValueChange = { qtyText ->
                    // Attempt to convert the entered text to an Int
                    quantity.value = qtyText.toIntOrNull() ?:0
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number),
                textStyle = MaterialTheme.typography.body1
            )
        }
    }
}

    /* Text(
        listingDetails.productCode,
        style = MaterialTheme.typography.subtitle1,
        fontWeight = FontWeight.SemiBold
    ) */

    /* Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "Bookmarked",
            style = MaterialTheme.typography.caption,
            fontWeight = FontWeight.SemiBold
        )
        Switch(
            checked = isBookmarked.value,
            onCheckedChange = {
                isBookmarked.value = it
            }
        ) */



@Preview(showBackground = true)
@Composable
fun ListingDetailScreenPreview() {
    WorkshopTheme {
        ListingDetailsScreen({ }, "ABC")
    }
}
