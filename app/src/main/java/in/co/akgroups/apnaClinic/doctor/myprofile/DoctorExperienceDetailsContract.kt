package `in`.co.akgroups.apnaClinic.doctor.myprofile

import `in`.co.akgroups.apnaClinic.BasePresenter
import `in`.co.akgroups.apnaClinic.BaseView
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.utils.ToastPosition

/**
 * Created by amitacharya on 15/2/20.
 */
interface DoctorExperienceDetailsContract {

    interface View : BaseView<DoctorExperienceDetailsContract.Presenter> {
        fun showProgressBar()

        fun hideProgressBar()

        fun showError(errorMessage : String)

        fun showDoctorExperience(userData: UserData)

        fun showToast(message: String, imageVisibility: Int, toastPosition: ToastPosition)
    }

    interface Presenter : BasePresenter {
        fun fetchDoctorExperience(userData: UserData)
        fun updateDoctorExperience(userData: UserData, exp: String, speciality: String, language: String)
    }
}