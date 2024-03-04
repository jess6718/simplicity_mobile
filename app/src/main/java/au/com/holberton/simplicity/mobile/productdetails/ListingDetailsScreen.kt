package au.com.holberton.simplicity.mobile.productdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import au.com.holberton.simplicity.mobile.common.CommonComponent
import au.com.holberton.simplicity.mobile.common.ExceptionHandler
import au.com.holberton.simplicity.mobile.productlisting.ListingDetailsRepository
import au.com.holberton.simplicity.mobile.ui.theme.WorkshopTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ListingDetailsScreen(onBackPressed: () -> Unit, upc: Long?, navigate: (String) -> Unit) {
    val listingDetails = remember { mutableStateOf<ListingDetails?>(null) }
    val scaffoldState = rememberScaffoldState()
    val snackbarHostState = remember { SnackbarHostState() }


    LaunchedEffect(true) {
        upc?.let {
            try {
                listingDetails.value = ListingDetailsRepository.getListingDetails(it)
            } catch (error: Exception) {
                ExceptionHandler.exceptionHandler(error, snackbarHostState)
            }
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
        bottomBar = {
            BottomAppBar(
                backgroundColor = Color.Transparent,
                contentColor = Color.Transparent,
                elevation = 0.dp, // Set the elevation to 0 to remove any shadow
                cutoutShape = CircleShape // Set the cutout shape to remove any visible shape
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.weight(1f)) // Add a spacer to push the button to the center

                    Button(
                        onClick = { navigate("home") },
                        modifier = Modifier
                            .padding(8.dp)
                            .height(64.dp)
                            .width(150.dp)
                    ) {
                        Text(text = "Home")
                    }
                    Spacer(modifier = Modifier.weight(1f)) // Add another spacer to push the button to the center
                }
            }
        },
        snackbarHost = { CommonComponent.TopSnackbarHost(snackbarHostState) }, // Integrate the custom Snackbar host
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            listingDetails?.value?.let {
                ListingDetailsView(it, snackbarHostState)
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun ListingDetailsView(
    listingDetails: ListingDetails,
    snackbarHostState: SnackbarHostState
) {

    val quantity = remember { mutableStateOf(listingDetails.quantity) }
    val upcString = listingDetails.upc.toString()
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current


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
                color = MaterialTheme.colors.secondary,
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 10.dp)
            )
            Text(
                text = "UPC Code: $upcString",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.secondary,
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
                color = MaterialTheme.colors.secondary,
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
                color = MaterialTheme.colors.secondary,
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
                color = MaterialTheme.colors.secondary,
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
                color = MaterialTheme.colors.secondary,
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
                    .focusRequester(focusRequester)
                    .height(55.dp)
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

                        keyboardController?.hide()

                        focusManager.clearFocus()

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
                Text(
                    text = "Update"
                )
            }
            Text(
                text = "",
                modifier = Modifier.focusRequester(focusRequester)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListingDetailScreenPreview() {
    WorkshopTheme {
        ListingDetailsScreen({ }, 9319133337497, { })
    }
}
