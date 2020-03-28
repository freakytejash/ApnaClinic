package `in`.co.akgroups.apnaClinic.doctor.appointment

import `in`.co.akgroups.apnaClinic.data.source.DataSourceInterface
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.data.source.local.prefs.AppPreferenceHelper
import io.reactivex.disposables.CompositeDisposable

class AppointmentsPresenter(
    val dataSourceInterface: DataSourceInterface,
    view: AppointmentContract.View,
    userData: UserData,
    val preferenceHelper: AppPreferenceHelper
) : AppointmentContract.Presenter {

    var compositeDisposable = CompositeDisposable()

    override fun fetchAppointments() {

    }

    override fun start() {

    }

    override fun stop() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}