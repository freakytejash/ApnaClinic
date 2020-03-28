package `in`.co.akgroups.apnaClinic.patient.medical_report

import `in`.co.akgroups.apnaClinic.BasePresenter
import `in`.co.akgroups.apnaClinic.BaseView
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.PatientMedicalReport
import `in`.co.akgroups.apnaClinic.utils.ToastPosition

interface DetailReportContract {

    interface View: BaseView<Presenter> {

        fun showProgressBar()

        fun hideProgressBar()

        fun showError(message: String)

        fun showSnackBarMessage(message: String)

        fun showToast(message: String, imageVisibility: Int, toastPosition: ToastPosition)

        fun showExpandedImage(attachment: PatientMedicalReport.Attachments)

        fun hideExpandedImage()
    }

    interface Presenter : BasePresenter {

        fun fetchMedicalReport()
    }
}