package `in`.co.akgroups.apnaClinic.doctor.cases

import `in`.co.akgroups.apnaClinic.data.source.DataSourceInterface
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.data.source.local.prefs.AppPreferenceHelper
import io.reactivex.disposables.CompositeDisposable

class CasesPresenter(
    val dataSourceInterface: DataSourceInterface,
    view: CasesContract.View,
    userData: UserData,
    val preferenceHelper: AppPreferenceHelper
) : CasesContract.Presenter {

    var compositeDisposable = CompositeDisposable()

    override fun fetchCases() {

    }

    override fun start() {

    }

    override fun stop() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}