package `in`.co.akgroups.apnaClinic.patient.profile.dependent

import `in`.co.akgroups.apnaClinic.BasePresenter
import `in`.co.akgroups.apnaClinic.BaseView
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.Dependent
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData


interface SelfProfileContract {

    interface View : BaseView<SelfProfileContract.Presenter> {

        fun showProgressBar()

        fun hideProgressBar()

        fun showError(errorMessage : String)

        fun showDependentList(dependentList: List<Dependent>)
    }

    interface Presenter : BasePresenter {

        fun convertUserDataToDependent(userData: UserData)
    }

}