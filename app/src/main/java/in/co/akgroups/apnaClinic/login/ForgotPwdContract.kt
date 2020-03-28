package `in`.co.akgroups.apnaClinic.login

import `in`.co.akgroups.apnaClinic.BasePresenter
import `in`.co.akgroups.apnaClinic.BaseView
import `in`.co.akgroups.apnaClinic.utils.ToastPosition

class ForgotPwdContract {

    interface View : BaseView<Presenter> {

        fun showProgressBar()

        fun hideProgressBar()

        fun showToast(message: String, imageVisibility: Int, toastPosition: ToastPosition)

        fun dismissFragment()

        fun showSnackBarMessage(message: String)

    }

    interface Presenter : BasePresenter {

        fun validateEmail(email: String)
    }
}