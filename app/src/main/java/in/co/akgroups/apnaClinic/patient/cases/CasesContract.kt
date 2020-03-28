package `in`.co.akgroups.apnaClinic.patient.cases

import `in`.co.akgroups.apnaClinic.BasePresenter
import `in`.co.akgroups.apnaClinic.BaseView

interface CasesContract {

    interface View : BaseView<CasesContract.Presenter> {

        fun showProgressBar()

        fun hideProgressBar()

        fun showError(errorMessage : String)
    }

    interface Presenter : BasePresenter {

        fun fetchCases()

    }
}