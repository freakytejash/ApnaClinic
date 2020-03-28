package `in`.co.akgroups.apnaClinic.patient.cases

import `in`.co.akgroups.apnaClinic.data.source.DataSourceInterface
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.data.source.local.prefs.AppPreferenceHelper
import io.reactivex.disposables.CompositeDisposable


class CasesPresenter(
    val dataSourceInterface: DataSourceInterface,
    consultView: CasesContract.View,
    userData: UserData,
    val preferenceHelper: AppPreferenceHelper
) : CasesContract.Presenter {

    var compositeDisposable = CompositeDisposable()

    override fun fetchCases() {
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