package `in`.co.akgroups.apnaClinic.doctor.myprofile

import `in`.co.akgroups.apnaClinic.data.source.DataSourceInterface
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.data.source.local.prefs.AppPreferenceHelper
import `in`.co.akgroups.apnaClinic.utils.ToastPosition
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by amitacharya on 15/2/20.
 */
class DoctorQualificationPresenter(
        val dataSourceInterface: DataSourceInterface,
        val dashboardView: DoctorQualificationContract.View,
        val userData: UserData,
        val preferenceHelper: AppPreferenceHelper
) : DoctorQualificationContract.Presenter {

    override fun updateDoctorQualification(userData: UserData, medicalboard: String, registrationno: String, graduate: String, postgraduate: String) {
        dashboardView.showProgressBar()
        compositeDisposable.add(
                dataSourceInterface.updateDoctorQualification(userData,medicalboard,registrationno,graduate,postgraduate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    dashboardView.hideProgressBar()
                    if (it?.status!!) {
                        dashboardView.showToast(it.msg!!, View.GONE, ToastPosition.BOTTOM)
                        dashboardView.hideProgressBar()
                    }
                }, {
                    dashboardView.hideProgressBar()
                })
        )
    }

    override fun fetchDoctorQualification(userData: UserData) {
        dashboardView.showProgressBar()
        compositeDisposable.add(
                dataSourceInterface.fetchDoctorQualification(userData)
                        .flatMap {
                            dataSourceInterface.fetchDoctorQualification(userData)
                        }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            dashboardView.hideProgressBar()
                            dashboardView.showDoctorQualification(userData)
                        }, {
                            dashboardView.hideProgressBar()
                        })
        )
    }



    var compositeDisposable = CompositeDisposable()
    override fun start() {

    }

    override fun stop() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

}