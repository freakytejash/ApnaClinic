package `in`.co.akgroups.apnaClinic.login

import `in`.co.akgroups.apnaClinic.BasePresenter
import `in`.co.akgroups.apnaClinic.BaseView
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.utils.ToastPosition

interface RegisterContract {

    interface View : BaseView<Presenter> {

        fun showProgressBar()

        fun hideProgressBar()

        fun showSnackBarMessage(message: String)

        fun openLoginPage()

        fun openOtpPage(userData: UserData)

        fun showToast(message: String, imageVisibility: Int, toastPosition: ToastPosition)

    }

    interface Presenter : BasePresenter {

        fun registerUser(
            firstName: String,
            lastName: String,
            email: String,
            phone: Long,
            password: String,
            type: Int
        )
    }
}