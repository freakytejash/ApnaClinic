package `in`.co.akgroups.apnaClinic.doctor.schedule

import `in`.co.akgroups.apnaClinic.data.source.DataSourceInterface
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.data.source.local.prefs.AppPreferenceHelper
import io.reactivex.disposables.CompositeDisposable

class SchedulePresenter(
    val dataSourceInterface: DataSourceInterface,
    view: ScheduleContract.View,
    userData: UserData,
    val preferenceHelper: AppPreferenceHelper
) : ScheduleContract.Presenter {

    var compositeDisposable = CompositeDisposable()

    override fun fetchSchedule() {

    }

    override fun start() {

    }

    override fun stop() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}