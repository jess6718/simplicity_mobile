package au.com.holberton.simplicity.mobile.barcodescanner.scanner

import androidx.lifecycle.LifecycleOwner

sealed class ScannerEvent {
    data class CreatePreviewView(val lifecycleOwner: LifecycleOwner): ScannerEvent() //data class: representing an event where a preview view needs to be created, The lifecycleOwner property holds the data associated with this event.
    object BottomSheetShown: ScannerEvent() //object: represent events without associated data as singleton instance
    object BottomSheetHidden: ScannerEvent()
    data class CameraRequiredDialogVisibility(val show: Boolean): ScannerEvent() //represent events with associated data
}
