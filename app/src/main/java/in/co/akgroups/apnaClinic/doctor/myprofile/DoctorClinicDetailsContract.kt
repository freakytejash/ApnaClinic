package `in`.co.akgroups.apnaClinic.doctor.myprofile

import `in`.co.akgroups.apnaClinic.BasePresenter
import `in`.co.akgroups.apnaClinic.BaseView
import `in`.co.akgroups.apnaClinic.doctor.dashboard.DashboardContract

/**
 * Created by amitacharya on 15/2/20.
 */
interface DoctorClinicDetailsContract {

    interface View : BaseView<DoctorClinicDetailsContract.Presenter> {
        fun showProgressBar()

        fun hideProgressBar()

        fun showError(errorMessage : String)
    }

    interface Presenter : BasePresenter {

        fun fetchDashboard()

    }
}