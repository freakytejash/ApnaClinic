package `in`.co.akgroups.apnaClinic.data.source.local.prefs

import `in`.co.akgroups.apnaClinic.utils.Const
import android.content.Context
import android.content.SharedPreferences
import android.support.annotation.VisibleForTesting

class AppPreferenceHelper : PreferenceHelper {

    private val KEY_SECTION = "section"
    private val KEY_ACTIONS_COUNT = "actions_count"
    private val KEY_CONTENT_PAGE = "content_page"
    private val KEY_MEDIA_RATING = "media_rating_page_number"
    private val KEY_MODEL_VERSION = "model_version"
    private val KEY_CACHE_VERSION = "cache_version"
    private val WELCOME_SCREEN = "welcome_screen"
    private val KEY_MEDIA_RATING_CACHE_VERSION = "media_rating_cache_version"

    private var sharedPref : SharedPreferences

    companion object {
        private var INSTANCE: AppPreferenceHelper? = null

        @JvmStatic
        fun getInstance(context: Context): AppPreferenceHelper {
            if (INSTANCE == null) {
                synchronized(AppPreferenceHelper::javaClass) {
                    INSTANCE = AppPreferenceHelper(context)
                }
            }
            return INSTANCE!!
        }

        @VisibleForTesting
        fun clearInstance() {
            INSTANCE = null
        }
    }


    private constructor(context: Context) {
        sharedPref = context.getSharedPreferences(Const.SHARED_PREF, Context.MODE_PRIVATE)
    }

    override fun test() {

    }
}