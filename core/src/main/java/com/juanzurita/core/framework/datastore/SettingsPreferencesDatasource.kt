package com.juanzurita.core.framework.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.resultadosfutbol.core.wear.framework.datastore.SettingsPreferencesConstants
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.preferences by preferencesDataStore(name = SettingsPreferencesConstants.PREFERENCES_NAME)

class SettingsPreferencesDatasource @Inject constructor(private val context: Context) {

    companion object {
        val LANG = stringPreferencesKey(SettingsPreferencesConstants.LANG_KEY)
        val ISOCODE = stringPreferencesKey(SettingsPreferencesConstants.ISOCODE_KEY)
    }

  /*  suspend fun getAppLang(): String {
        return context.preferences.data.map { prefs ->
            prefs[LANG] ?: SettingsPreferencesConstants.LANG_DEFAULT
        }.firstOrNull()?: SettingsPreferencesConstants.LANG_DEFAULT
    }

    suspend fun updateAppLang(lang: String?) {
        context.preferences.edit { prefs ->
            prefs[LANG] = lang ?: SettingsPreferencesConstants.LANG_DEFAULT
        }
    }
*/
}