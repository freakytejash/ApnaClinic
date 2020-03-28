package `in`.co.akgroups.apnaClinic.patient.profile

import `in`.co.akgroups.apnaClinic.BasePresenter
import `in`.co.akgroups.apnaClinic.BaseView
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.utils.ToastPosition

class UserProfileContract {

    interface View: BaseView<Presenter>
    {
        fun showProgressBar()

        fun hideProgressBar()

        fun showToast(message: String, imageVisibility: Int, toastPosition: ToastPosition)

        fun showSnackBarMessage(message: String)

        fun setupViewPager()
    }
    interface Presenter: BasePresenter
    {
        fun fetchUserProfile(userData: UserData)
    }
}