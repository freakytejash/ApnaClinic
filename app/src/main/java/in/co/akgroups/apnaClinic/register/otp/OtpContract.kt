package `in`.co.akgroups.apnaClinic.register.otp

import `in`.co.akgroups.apnaClinic.BasePresenter
import `in`.co.akgroups.apnaClinic.BaseView
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.utils.ToastPosition

/**
 * Created by narendrapal on 15/01/2020
 */

class OtpContract {

    interface View : BaseView<Presenter> {

        fun showProgressBar()

        fun hideProgressBar()

        fun showSnackBarMessage(message: String)

        fun openLoginPage()

        fun openPatientPanel(userData: UserData)

        fun openDoctorPanel(userData: UserData)

        fun showToast(message: String, imageVisibility: Int, toastPosition: ToastPosition)

    }

    interface Presenter : BasePresenter {

        fun submitOtp(userData: UserData, mobileOtp: Int, emailOtp: Int)

        fun submitLoginOtp(userData: UserData, otp: Int)
    }
}