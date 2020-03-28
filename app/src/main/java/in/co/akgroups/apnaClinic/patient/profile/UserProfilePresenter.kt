package `in`.co.akgroups.apnaClinic.patient.profile

import `in`.co.akgroups.apnaClinic.data.source.DataSourceInterface
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.data.source.local.prefs.AppPreferenceHelper
import io.reactivex.disposables.CompositeDisposable

class UserProfilePresenter(
    dataSourceInterface: DataSourceInterface, view: UserProfileContract.View,
    userData: UserData
) :
    UserProfileContract.Presenter {

    var mDataSourceInterface: DataSourceInterface = dataSourceInterface
    var view: UserProfileContract.View
    var compositeDisposable = CompositeDisposable()

    init {
        this.view = view
        this.view.presenter = this
    }

    override fun fetchUserProfile(userData: UserData) {

    }

    override fun start() {

    }

    override fun stop() {

    }
}