package `in`.co.akgroups.apnaClinic.patient.recent

import `in`.co.akgroups.apnaClinic.BasePresenter
import `in`.co.akgroups.apnaClinic.BaseView

interface RecentContract {

    interface View : BaseView<RecentContract.Presenter> {

        fun showProgressBar()

        fun hideProgressBar()

        fun showError(errorMessage : String)
    }

    interface Presenter : BasePresenter {

        fun fetchRecentData()

    }
}