package au.com.holberton.simplicity.mobile.barcodescanner.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import au.com.holberton.simplicity.mobile.barcodescanner.ui.theme.DarkGrey
import au.com.holberton.simplicity.mobile.barcodescanner.ui.theme.LightYellow
import au.com.holberton.simplicity.mobile.barcodescanner.model.Scan
import au.com.holberton.simplicity.mobile.barcodescanner.model.ScanType
import au.com.holberton.simplicity.mobile.R

@Composable
fun ScanSheet(
    scan: Scan,
    modifier: Modifier = Modifier,
    navigate: (String) -> Unit,
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        Button(
            onClick = {},
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .height(12.dp)
                .width(50.dp)
                .padding(top = 5.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = DarkGrey,
                contentColor = LightYellow
            )
        ) {}
        Text(
            text = "Product Code",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold
        )
        if (scan.displayValue.isNotBlank()) {
            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 4.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable{navigate("listingDetails/${scan.displayValue}")}
            ) {
                Text(
                    text = scan.displayValue,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(2.dp))
    }
}