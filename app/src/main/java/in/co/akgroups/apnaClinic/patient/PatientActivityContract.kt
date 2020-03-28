package `in`.co.akgroups.apnaClinic.patient

import `in`.co.akgroups.apnaClinic.BasePresenter
import `in`.co.akgroups.apnaClinic.BaseView
import `in`.co.akgroups.apnaClinic.utils.ToastPosition

interface PatientActivityContract {

    interface View : BaseView<Presenter> {
        fun setToolbar()

        fun setupViewPager()

        fun setupClickListener()

        fun setupNavigationView()

        fun openMedicalHistoryScreen()

        fun openMedicalReportsScreen()

        fun openUserProfileScreen()

        fun openChangePasswordScreen()

        fun performLogout()

        fun showToast(message: String, imageVisibility: Int, toastPosition: ToastPosition)
    }

    interface Presenter : BasePresenter {

    }
}