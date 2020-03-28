package `in`.co.akgroups.apnaClinic.doctor.schedule

import `in`.co.akgroups.apnaClinic.BasePresenter
import `in`.co.akgroups.apnaClinic.BaseView

interface ScheduleContract {

    interface View : BaseView<ScheduleContract.Presenter> {
        fun showProgressBar()

        fun hideProgressBar()

        fun showError(errorMessage : String)
    }

    interface Presenter : BasePresenter {

        fun fetchSchedule()

    }
}