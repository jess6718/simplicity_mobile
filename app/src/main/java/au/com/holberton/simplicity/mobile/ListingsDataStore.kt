package au.com.holberton.simplicity.mobile

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ListingsDataStore(private val context: Context) {
    companion object {
        const val PREFERENCE_FILENAME = "preference_filename"
        val PREFERENCE_KEY_BOOKMARKED = booleanPreferencesKey("preference_key_bookmarked")

        @Volatile
        private var INSTANCE: ListingsDataStore? = null
        // Should NOT create more than one instance of DataStore for a given file
        fun getInstance(context: Context): ListingsDataStore =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: ListingsDataStore(context).also { INSTANCE = it }
            }
    }

    private val Context.dataStore by preferencesDataStore(name = PREFERENCE_FILENAME)

    val bookmarkedFlow: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[PREFERENCE_KEY_BOOKMARKED] ?: false
        }

    suspend fun bookmark(isBookmarked: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PREFERENCE_KEY_BOOKMARKED] = isBookmarked
        }
    }
}
