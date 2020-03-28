package `in`.co.akgroups.apnaClinic.patient.profile.dependent

import `in`.co.akgroups.apnaClinic.BasePresenter
import `in`.co.akgroups.apnaClinic.BaseView
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.Dependent
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData

interface DependentContract {
    interface View : BaseView<Presenter> {
        fun showProgressBar()

        fun hideProgressBar()

        fun showError(errorMessage: String)

        fun showDependentList(list: List<Dependent>)
    }

    interface Presenter : BasePresenter {
        fun fetchDependentData(mUserData: UserData)
    }
}