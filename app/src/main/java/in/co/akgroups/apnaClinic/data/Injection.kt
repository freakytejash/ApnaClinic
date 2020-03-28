package `in`.co.akgroups.apnaClinic.data


import `in`.co.akgroups.apnaClinic.data.source.DataRepository
import `in`.co.akgroups.apnaClinic.data.source.DataSourceInterface
import `in`.co.akgroups.apnaClinic.data.source.local.db.AppDatabase
import `in`.co.akgroups.apnaClinic.data.source.local.db.RoomDBHelper
import `in`.co.akgroups.apnaClinic.data.source.local.prefs.AppPreferenceHelper
import `in`.co.akgroups.apnaClinic.data.source.parser.AppParser
import `in`.co.akgroups.apnaClinic.data.source.remote.ApiService
import `in`.co.akgroups.apnaClinic.data.source.remote.AppApiHelper
import android.content.Context

object Injection {

    fun provideDataRepository(context: Context): DataSourceInterface {
        val database = AppDatabase.getInstance(context)
        return DataRepository.getInstance(
            RoomDBHelper.getInstance(database),
            AppApiHelper(ApiService.create()),
            AppParser.getInstance(),
            AppPreferenceHelper.getInstance(context))
    }
}