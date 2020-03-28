package `in`.co.akgroups.apnaClinic.patient.recent

import `in`.co.akgroups.apnaClinic.data.source.DataSourceInterface
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.data.source.local.prefs.AppPreferenceHelper
import `in`.co.akgroups.apnaClinic.patient.dashboard.DashboardContract
import io.reactivex.disposables.CompositeDisposable

class RecentPresenter(
    val dataSourceInterface: DataSourceInterface,
    recentView: RecentContract.View,
    userData: UserData,
    val preferenceHelper: AppPreferenceHelper
) : RecentContract.Presenter {

    var compositeDisposable = CompositeDisposable()

    override fun fetchRecentData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun stop() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}