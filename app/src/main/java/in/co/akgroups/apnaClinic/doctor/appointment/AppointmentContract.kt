package `in`.co.akgroups.apnaClinic.doctor.appointment

import `in`.co.akgroups.apnaClinic.BasePresenter
import `in`.co.akgroups.apnaClinic.BaseView

interface AppointmentContract {

    interface View : BaseView<AppointmentContract.Presenter> {
        fun showProgressBar()

        fun hideProgressBar()

        fun showError(errorMessage : String)
    }

    interface Presenter : BasePresenter {

        fun fetchAppointments()

    }
}