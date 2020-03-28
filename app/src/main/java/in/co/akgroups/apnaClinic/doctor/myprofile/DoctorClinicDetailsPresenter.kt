package `in`.co.akgroups.apnaClinic.doctor.myprofile

import `in`.co.akgroups.apnaClinic.data.source.DataSourceInterface
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.data.source.local.prefs.AppPreferenceHelper
import `in`.co.akgroups.apnaClinic.doctor.dashboard.DashboardContract
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by amitacharya on 15/2/20.
 */
class DoctorClinicDetailsPresenter(
        val dataSourceInterface: DataSourceInterface,
        dashboardView: DoctorClinicDetailsContract.View,
        userData: UserData,
        val preferenceHelper: AppPreferenceHelper
):DoctorClinicDetailsContract.Presenter {

    var compositeDisposable = CompositeDisposable()
    override fun start() {

    }

    override fun stop() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    override fun fetchDashboard() {

    }

}