package `in`.co.akgroups.apnaClinic.patient.consult

import `in`.co.akgroups.apnaClinic.BasePresenter
import `in`.co.akgroups.apnaClinic.BaseView

interface ConsultContract {

    interface View : BaseView<ConsultContract.Presenter> {

        fun showProgressBar()

        fun hideProgressBar()

        fun showError(errorMessage : String)
    }

    interface Presenter : BasePresenter {

        fun fetchConsult()

    }
}