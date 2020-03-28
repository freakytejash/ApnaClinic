package `in`.co.akgroups.apnaClinic.patient.consult

import `in`.co.akgroups.apnaClinic.data.source.DataSourceInterface
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.data.source.local.prefs.AppPreferenceHelper
import io.reactivex.disposables.CompositeDisposable

class ConsultPresenter(
    val dataSourceInterface: DataSourceInterface,
    consultView: ConsultContract.View,
    userData: UserData,
    val preferenceHelper: AppPreferenceHelper
) : ConsultContract.Presenter {

    var compositeDisposable = CompositeDisposable()

    override fun fetchConsult() {
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