package au.com.holberton.simplicity.mobile.productdetails

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.ButtonDefaults
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import au.com.holberton.simplicity.mobile.common.CommonComponent
import au.com.holberton.simplicity.mobile.common.ExceptionHandler
import au.com.holberton.simplicity.mobile.productlisting.ListingDetailsRepository
import au.com.holberton.simplicity.mobile.ui.theme.WorkshopTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
                title = { Text(text = "Product Details") },
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
                elevation = 0.dp,
                cutoutShape = CircleShape
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
                            .width(150.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFFECAC5F)
                        )
                    ) {
                        Text(text = "Home",
                            color = Color.White)
                    }
                    Spacer(modifier = Modifier.weight(1f)) // Add another spacer to push the button to the center
                }
            }
        },
        snackbarHost = { CommonComponent.TopSnackbarHost(snackbarHostState) },
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            listingDetails.value?.let {
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
    val bitmapState = remember { mutableStateOf<Bitmap?>(null) }

    LaunchedEffect(key1 = listingDetails._id) {
        withContext(Dispatchers.IO) {
            try {
                val bitmap = ListingDetailsRepository.getItemImageById(listingDetails._id)
                bitmapState.value = bitmap
            } catch (e: Exception) {
                Log.e("ListingCard", "Error fetching image: ${e.message}")
            }
        }
    }

        bitmapState.value?.let { bitmap ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
            ) {
                Image(
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = "Listing image",
                    modifier = Modifier
                        .height(180.dp)
                        .width(180.dp),
                    contentScale = ContentScale.FillBounds
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
                    .padding(top = 10.dp)
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF6A0DAD),
                                fontSize = 16.sp
                            )
                        ) {
                            append("Name:")
                        }
                        append(" ${listingDetails.name}")
                    },
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.secondary,
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 13.dp)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF6A0DAD),
                                fontSize = 16.sp
                            )
                        ) {
                            append("UPC:")
                        }
                        append(" $upcString")
                    },
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.secondary,
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 13.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF6A0DAD),
                                fontSize = 16.sp
                            )
                        ) {
                            append("Price:")
                        }
                        append(" ${listingDetails.salePrice}")
                    },
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.secondary,
                    modifier = Modifier.padding(vertical = 13.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF6A0DAD),
                                fontSize = 16.sp
                            )
                        ) {
                            append("Location:")
                        }
                        append(" ${listingDetails.location}")
                    },
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.secondary,
                    modifier = Modifier.padding(vertical = 13.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF6A0DAD),
                                fontSize = 16.sp
                            )
                        ) {
                            append("Category:")
                        }
                        append(" ${listingDetails.category}")
                    },
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.secondary,
                    modifier = Modifier.padding(vertical = 13.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF6A0DAD)
                            )
                        ) {
                            append("Quantity:")
                        }
                    },
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.secondary,
                    modifier = Modifier
                        .padding()
                )
                OutlinedTextField(
                    value = if (quantity.value == null) "" else quantity.value.toString(),
                    onValueChange = { qtyText ->
                        // Attempt to convert the entered text to an Int
                        if (qtyText.isEmpty()) {
                            quantity.value = null
                        } else {
                            // Allow input of 0 if the conversion succeeds
                            if (qtyText.toIntOrNull() != null) {
                                quantity.value = qtyText.toIntOrNull()
                            }
                        }
                    },
                    modifier = Modifier // text box
                        .focusRequester(focusRequester)
                        .height(53.dp)
                        .width(135.dp)
                        .padding(start = 10.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    textStyle = MaterialTheme.typography.body1
                )
                Button(
                    onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            // Call the API to update item quantity
                            ListingDetailsRepository.updateItemQty(
                                listingDetails.upc,
                                quantity.value
                            )

                            keyboardController?.hide()

                            focusManager.clearFocus()

                            // Show the success message
                            snackbarHostState.showSnackbar(
                                message = "Update successful",
                                duration = SnackbarDuration.Short
                            )
                        }
                    },
                    modifier = Modifier // update button
                        .height(42.dp)
                        .width(110.dp)
                        .padding(start = 11.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFECAC5F))
                ) {
                    Text(
                        text = "Update",
                        style = MaterialTheme.typography.button,
                        color = Color.White
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
