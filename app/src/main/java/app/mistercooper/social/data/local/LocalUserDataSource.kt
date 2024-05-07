package app.mistercooper.social.data.local

import android.content.SharedPreferences
import javax.inject.Inject

class LocalUserDataSource @Inject constructor(private val sharedPrefs: SharedPreferences) {

    private val keyApiKey = "api_key"

    suspend fun saveApiKey(apiKey: String){
        with (sharedPrefs.edit()) {
            putString(keyApiKey, apiKey)
            commit()
        }
    }

    fun getApiKey() = sharedPrefs.getString(keyApiKey, "")

}