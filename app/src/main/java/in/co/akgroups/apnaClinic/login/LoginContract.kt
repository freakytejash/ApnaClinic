package `in`.co.akgroups.apnaClinic.login

import `in`.co.akgroups.apnaClinic.BasePresenter
import `in`.co.akgroups.apnaClinic.BaseView
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.utils.ToastPosition

interface LoginContract {

    interface View : BaseView<Presenter> {

        fun showProgressBar()

        fun hideProgressBar()

        fun showError(errorMessage : String)

        fun showToast(message: String, imageVisibility: Int, toastPosition: ToastPosition)

        fun openDoctorPanel(userData: UserData)

        fun openOtpPage(userData: UserData)

        fun openPatientPanel(userData: UserData)

        fun openSignUpPage()

    }

    interface Presenter : BasePresenter {

        fun checkIfUserAlreadyLoggedIn()

        fun loginUser(loginId: String, password: String, type: Int)
    }
}