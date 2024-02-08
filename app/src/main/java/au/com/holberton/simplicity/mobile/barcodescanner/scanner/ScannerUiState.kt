package au.com.holberton.simplicity.mobile.barcodescanner.scanner

import androidx.camera.view.PreviewView
import au.com.holberton.simplicity.mobile.barcodescanner.model.Scan

data class ScannerUiState(
    val previewView: PreviewView? = null,
    val scan: Scan? = null,
    val showBottomSheet: Boolean = false,
    val showCameraRequiredDialog: Boolean = true
)
