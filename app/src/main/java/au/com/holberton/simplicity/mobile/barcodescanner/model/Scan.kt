package au.com.holberton.simplicity.mobile.barcodescanner.model

import androidx.annotation.StringRes

data class Scan(
    val displayValue: String,
    @StringRes val scanFormatId: Int,
    val scanType: ScanType
)

enum class ScanType {
    Text,
    Url
}
