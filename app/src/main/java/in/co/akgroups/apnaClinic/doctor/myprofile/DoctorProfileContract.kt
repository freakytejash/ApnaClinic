package `in`.co.akgroups.apnaClinic.doctor.myprofile

import `in`.co.akgroups.apnaClinic.BasePresenter
import `in`.co.akgroups.apnaClinic.BaseView
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData

/**
 * Created by amitacharya on 15/2/20.
 */
class DoctorProfileContract {
    interface View : BaseView<Presenter> {

        fun showProgressBar()

        fun hideProgressBar()

        fun showDoctorProfile(userData: UserData)

        fun showSnackbarMessage(message: String)
    }

    interface Presenter : BasePresenter {
        fun fetchDoctorProfile(userData: UserData)
    }
}