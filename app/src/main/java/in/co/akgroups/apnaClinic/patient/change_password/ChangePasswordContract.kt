package `in`.co.akgroups.apnaClinic.patient.change_password

import `in`.co.akgroups.apnaClinic.BasePresenter
import `in`.co.akgroups.apnaClinic.BaseView
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.utils.ToastPosition

class ChangePasswordContract {

    interface View : BaseView<Presenter> {

        fun showProgressBar()

        fun hideProgressBar()

        fun showToast(message: String, imageVisibility: Int, toastPosition: ToastPosition)

        fun dismissFragment()

        fun showSnackBarMessage(message: String)

    }

    interface Presenter : BasePresenter {
        fun changePassword(userData: UserData, currentPassword: String, newPassword: String)
    }
}