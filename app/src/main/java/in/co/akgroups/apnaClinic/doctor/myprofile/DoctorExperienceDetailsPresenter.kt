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
class DoctorExperienceDetailsPresenter(
        val dataSourceInterface: DataSourceInterface,
        val dashboardView: DoctorExperienceDetailsContract.View,
        val userData: UserData,
        val preferenceHelper: AppPreferenceHelper
) : DoctorExperienceDetailsContract.Presenter {

    override fun fetchDoctorExperience(userData: UserData) {
        dashboardView.showProgressBar()
        compositeDisposable.add(
                dataSourceInterface.fetchDoctorExperience(userData)
                        .flatMap {
                            dataSourceInterface.fetchDoctorExperience(userData)
                        }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            dashboardView.hideProgressBar()
                            dashboardView.showDoctorExperience(userData)
                        }, {
                            dashboardView.hideProgressBar()
                        })
        )
    }

    override fun updateDoctorExperience(userData: UserData, exp: String, speciality: String, language: String) {
        dashboardView.showProgressBar()
        compositeDisposable.add(
                dataSourceInterface.updateDoctorExperience(userData, exp, speciality, language)
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

    var compositeDisposable = CompositeDisposable()
    override fun start() {

    }

    override fun stop() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

}