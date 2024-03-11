package au.com.holberton.simplicity.mobile.homepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import au.com.holberton.simplicity.mobile.R
import au.com.holberton.simplicity.mobile.ui.theme.WorkshopTheme

@Composable
fun HomePageScreen(navigate: (String) -> Unit) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.inventoryimage1),
                contentDescription = "inventoryimage1",
                modifier = Modifier
                    .size(370.dp)
                    .offset(y = (-50).dp)
            )
            Row(
                modifier = Modifier
                    .offset(y = (-85).dp)
                    .padding(bottom = 35.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "logo",
                    modifier = Modifier
                        .size(70.dp)
                )
                Text(
                    text = "Simplicity",
                    fontSize = 37.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.ExtraBold,
                    fontStyle = FontStyle.Italic,
                    style = androidx.compose.ui.text.TextStyle(
                        shadow = Shadow(
                            color = Color.Gray,
                            offset = Offset(4f, 4f),
                            blurRadius = 8f
                        )
                    ),
                    color = Color(0xFF6C99EE),
                    modifier = Modifier.padding(start = 30.dp)
                )
            }
            Row(
                modifier = Modifier
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(41.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .clickable { navigate("scanner") }
                        .size(width = 145.dp, height = 170.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFF806CEE)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(25.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        Image(
                            painter = painterResource(id = R.drawable.barcode),
                            contentDescription = "barcode",
                            modifier = Modifier
                                .size(80.dp)
                        )
                        Text(
                            text = "Scan",
                            fontSize = 20.sp,
                            color = Color.White,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic,
                        )
                        Spacer(modifier = Modifier.weight(0.1f))
                    }
                }
                Box(
                    modifier = Modifier
                        .clickable { navigate("listings") }
                        .size(width = 145.dp, height = 170.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFFECAC5F))
                        .padding(10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = MaterialTheme.colors.onPrimary,
                            modifier = Modifier.size(100.dp).offset(y = (-8).dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "Search",
                            fontSize = 20.sp,
                            color = Color.White,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic,
                        )
                        Spacer(modifier = Modifier.weight(0.1f))
                    }
                }
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
