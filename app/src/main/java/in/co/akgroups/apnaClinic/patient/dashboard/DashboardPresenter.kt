package `in`.co.akgroups.apnaClinic.patient.dashboard

import `in`.co.akgroups.apnaClinic.data.source.DataSourceInterface
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.data.source.local.prefs.AppPreferenceHelper
import io.reactivex.disposables.CompositeDisposable

class DashboardPresenter(
    val dataSourceInterface: DataSourceInterface,
    dashboardView: DashboardContract.View,
    userData: UserData,
    val preferenceHelper: AppPreferenceHelper
) :
    DashboardContract.Presenter {

    var compositeDisposable = CompositeDisposable()

    override fun fetchDashboard() {
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