package `in`.co.akgroups.apnaClinic.doctor.myprofile

import `in`.co.akgroups.apnaClinic.BasePresenter
import `in`.co.akgroups.apnaClinic.BaseView
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.utils.ToastPosition

/**
 * Created by amitacharya on 15/2/20.
 */
interface DoctorQualificationContract {
    interface View : BaseView<DoctorQualificationContract.Presenter> {
        fun showProgressBar()

        fun hideProgressBar()

        fun showError(errorMessage : String)

        fun showDoctorQualification(userData: UserData)

        fun showToast(message: String, imageVisibility: Int, toastPosition: ToastPosition)
    }

    interface Presenter : BasePresenter {
        fun fetchDoctorQualification(userData: UserData)
        fun updateDoctorQualification(userData: UserData, medicalboard: String, registrationno: String, graduate: String, postgraduate: String)
    }
}