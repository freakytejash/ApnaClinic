package `in`.co.akgroups.apnaClinic.doctor.dashboard

import `in`.co.akgroups.apnaClinic.BasePresenter
import `in`.co.akgroups.apnaClinic.BaseView

interface DashboardContract {

    interface View : BaseView<DashboardContract.Presenter> {
        fun showProgressBar()

        fun hideProgressBar()

        fun showError(errorMessage : String)
    }

    interface Presenter : BasePresenter {

        fun fetchDashboard()

    }
}