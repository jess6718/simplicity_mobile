package au.com.holberton.simplicity.mobile.common

import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import java.lang.Exception

object ExceptionHandler {
    suspend fun exceptionHandler(exception: Exception, snackbarHostState: SnackbarHostState): Unit {
        if (exception is HttpException) {
            val errorBody = exception.response()?.errorBody()?.string()
            if (errorBody != null) {
                try {
                    val jsonObject = JSONObject(errorBody)
                    val errorMessage = jsonObject.getString("message")
                    snackbarHostState.showSnackbar(
                        message = errorMessage,
                        duration = SnackbarDuration.Short
                    )
                } catch (e: JSONException) {
                    // If there's an error parsing the JSON response, show a generic error message
                    snackbarHostState.showSnackbar(
                        message = "API response incorrect format",
                        duration = SnackbarDuration.Short
                    )
                }
            }
        } else {
            snackbarHostState.showSnackbar(
                message = "Server response fails",
                duration = SnackbarDuration.Short
            )
        }
    }
}