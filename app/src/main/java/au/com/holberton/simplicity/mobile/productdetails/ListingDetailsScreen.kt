package au.com.holberton.simplicity.mobile.productdetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ListingDetailsScreen(onBackPressed: () -> Unit, upc: Double?) {
    val listingDetails = remember { mutableStateOf<ListingDetails?>(null) }
    val scaffoldState = rememberScaffoldState()
    val snackbarHostState = remember { SnackbarHostState() }


    // TODO task 4.2: Fetch data for listing details screen
    LaunchedEffect(true) {
        upc?.let {
            listingDetails.value = ListingDetailsRepository.getListingDetails(it)
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
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
        },
        snackbarHost = { TopSnackbarHost(snackbarHostState) } // Integrate the custom Snackbar host
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            listingDetails?.value?.let {
                ListingDetailsView(it, scaffoldState, snackbarHostState)
            }
        }
    }
}

// TODO bonus challenge: Complete Listing Details Screen
@Composable
private fun ListingDetailsView(
    listingDetails: ListingDetails,
    scaffoldState: ScaffoldState,
    snackbarHostState: SnackbarHostState
) {

    val quantity = remember { mutableStateOf(listingDetails.quantity) }
    val upcString = String.format("%.0f", listingDetails.upc)

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
                text = "Name: ${listingDetails.name}",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.primary,
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 10.dp)
            )
            Text(
                text = "UPC Code: $upcString",
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
                text = "Price: ${listingDetails.salePrice}",
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
                text = "Category: ${listingDetails.category}",
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
                value = if (quantity.value == null) "" else quantity.value.toString(),
                onValueChange = { qtyText ->
                    // Attempt to convert the entered text to an Int
                    if(qtyText.isEmpty()){
                        quantity.value = null
                    } else {
                        // Allow input of 0 if the conversion succeeds
                        if (qtyText.toIntOrNull() != null) {
                            quantity.value = qtyText.toIntOrNull()
                        }
                    }
                },
                modifier = Modifier
                    .height(50.dp)
                    .width(150.dp)
                    .padding(start = 8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number),
                textStyle = MaterialTheme.typography.body1
            )
            Button(
                onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        // Call the API to update item quantity
                        ListingDetailsRepository.updateItemQty(listingDetails.upc, quantity.value)

                        // Show the success message
                        snackbarHostState.showSnackbar(
                            message = "Update successful",
                            duration = SnackbarDuration.Short
                        )
                    }
                },
                modifier = Modifier
                    .height(40.dp)
                    .width(100.dp)
                    .padding(start = 10.dp)
            ) {
                Text(text = "update")
            }
        }
    }
}

@Composable
fun TopSnackbarHost(
    snackbarHostState: SnackbarHostState
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        SnackbarHost(
            hostState = snackbarHostState,
            snackbar = { data ->
                Snackbar(
                    snackbarData = data,
                    modifier = Modifier.align(Alignment.TopCenter)
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ListingDetailScreenPreview() {
    WorkshopTheme {
        ListingDetailsScreen({ }, 9319133337497.00)
    }
}
