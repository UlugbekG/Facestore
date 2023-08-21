package cd.ghost.source

import android.content.Context
import cd.ghost.source.settings.AppSettings
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPreferencesAppSettings @Inject constructor(
    @ApplicationContext private val context: Context
) : AppSettings {

    private val sharedPreferences =
        context.getSharedPreferences(APP_SETTINGS_KEY, Context.MODE_PRIVATE)

    override fun getCurrentToken(): String? =
        sharedPreferences.getString(PREF_CURRENT_ACCOUNT_TOKEN, null)

    override fun setCurrentToken(token: String?) {
        val editor = sharedPreferences.edit()
        if (token == null) editor.remove(PREF_CURRENT_ACCOUNT_TOKEN)
        else editor.putString(PREF_CURRENT_ACCOUNT_TOKEN, token)
        editor.apply()
    }

    companion object {
        const val APP_SETTINGS_KEY = "app_settings"
        const val PREF_CURRENT_ACCOUNT_TOKEN = "current_token"
    }

}