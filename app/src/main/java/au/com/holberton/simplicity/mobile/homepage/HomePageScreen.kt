package au.com.holberton.simplicity.mobile.homepage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import au.com.holberton.simplicity.mobile.ui.theme.WorkshopTheme

@Composable
fun HomePageScreen(navigate: (String) -> Unit) {
    Row(
        modifier = Modifier
            .padding(top=100.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .clickable { navigate("listings") }
                .size(width = 200.dp, height = 150.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colors.primary)
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp), // Adjust the spacing as needed
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Image on top
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null, // Content description for accessibility
                    tint = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.size(24.dp)
                )
                // Text at bottom
                Text(
                    text = "Search",
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }
        Box(
            modifier = Modifier
                .clickable { navigate("scanner") }
                .size(width = 200.dp, height = 150.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colors.primary)
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp), // Adjust the spacing as needed
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Image on top
                Icon(
                    imageVector = Icons.Filled.ShoppingCart,
                    contentDescription = null, // Content description for accessibility
                    tint = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.size(24.dp)
                )
                // Text at bottom
                Text(
                    text = "Scan",
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePageScreenPreview() {
    WorkshopTheme {
        HomePageScreen { }
    }
}