package `in`.co.akgroups.apnaClinic.splash

import `in`.co.akgroups.apnaClinic.BasePresenter
import `in`.co.akgroups.apnaClinic.BaseView
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData


interface SplashContract {

    interface View : BaseView<Presenter> {

        fun showProgressBar()

        fun hideProgressBar()

        fun openLoginActivity()

        fun openPatientPanel(userData: UserData)

        fun openDoctorPanel(userData: UserData)

        fun showError(errorMessage : String)

    }

    interface Presenter : BasePresenter {

        fun checkIfUserAlreadyLoggedIn()
    }
}