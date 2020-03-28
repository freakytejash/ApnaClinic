package `in`.co.akgroups.apnaClinic.doctor.myprofile

import `in`.co.akgroups.apnaClinic.data.source.DataSourceInterface
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by narendrapal on 19/01/2020
 */

class DoctorProfilePresenter(
        val dataSourceInterface: DataSourceInterface,
        val mView: DoctorProfileContract.View,
        var userData: UserData
) : DoctorProfileContract.Presenter {

    val compositeDisposable = CompositeDisposable()

    override fun fetchDoctorProfile(userData: UserData) {
        mView.showProgressBar()
        compositeDisposable.add(
            dataSourceInterface.fetchDoctorProfile(userData)
                .flatMap {
                    dataSourceInterface.fetchDoctorExperience(userData)
                }
                .flatMap {
                    dataSourceInterface.fetchDoctorQualification(userData)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mView.hideProgressBar()
                    mView.showDoctorProfile(userData)
                }, {
                    mView.hideProgressBar()
                })
        )
    }

    override fun start() {
    }

    override fun stop() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}