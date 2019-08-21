package jeremypacabis.mlist

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import jeremypacabis.mlist.models.api.APIClient
import jeremypacabis.mlist.models.persistence.MediaDb
import jeremypacabis.mlist.utils.Logger
import jeremypacabis.mlist.utils.PREFERENCES_FILE
import jeremypacabis.mlist.utils.PREF_KEY_LAST_VISIT

/**
 * Created by jeremypacabis on Aug 16, 2019 @ 11:12.
 * @author Jeremy Patrick Pacabis <jeremypacabis@gmail.com>
 * jeremypacabis.mlist <mlist>
 */
typealias MList = MListApplication

class MListApplication : Application() {

    companion object {
        lateinit var preferences: SharedPreferences
        lateinit var apiClient: APIClient
        lateinit var mediaDatabase: MediaDb
        var lastVisitTimestampText: String?
            get() = preferences.getString(PREF_KEY_LAST_VISIT, "")
            set(value) = preferences.edit().putString(PREF_KEY_LAST_VISIT, value).apply()
    }

    override fun onCreate() {
        super.onCreate()
        Logger.d { "MList Application created..." }
        initialize()
    }

    private fun initialize() {
        preferences = applicationContext.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE)
        mediaDatabase = MediaDb.getInstance(applicationContext)!!
        apiClient = APIClient(
            applicationContext.getString(R.string.media_search_base_url),
            applicationContext
        )
    }
}