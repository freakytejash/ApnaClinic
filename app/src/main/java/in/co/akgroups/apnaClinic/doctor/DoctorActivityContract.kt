package `in`.co.akgroups.apnaClinic.doctor

import `in`.co.akgroups.apnaClinic.BasePresenter
import `in`.co.akgroups.apnaClinic.BaseView
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.utils.ToastPosition

interface DoctorActivityContract {

    interface View : BaseView<Presenter> {

        fun setToolbar()

        fun setupViewPager()

        fun setupClickListener()

        fun setupNavigationView()

        fun openChangePasswordScreen()

        fun performLogout()

        fun showToast(message: String, imageVisibility: Int, toastPosition: ToastPosition)

        fun showError(errorMessage: String)

        fun openMyProfileScreen(userData: UserData)

        fun openMyAccountsScreen(userData: UserData)

        fun openTerms_N_ConditionScreen()

        fun openFeedbackScreen()
    }

    interface Presenter : BasePresenter {

    }

}