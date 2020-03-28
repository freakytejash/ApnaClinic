package `in`.co.akgroups.apnaClinic.patient.medical_report

import `in`.co.akgroups.apnaClinic.BasePresenter
import `in`.co.akgroups.apnaClinic.BaseView
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.PatientMedicalReport
import `in`.co.akgroups.apnaClinic.patient.medical_report.adapter.MedicalReportAdapter
import `in`.co.akgroups.apnaClinic.utils.ToastPosition

interface MedicalReportContract {

    interface View: BaseView<Presenter>{

        fun showProgressBar()

        fun hideProgressBar()

        fun showError(message: String)

        fun showSnackBarMessage(message: String)

        fun showRetry()

        fun showMedicalReport(list: List<PatientMedicalReport>)

        fun showNoReportLayout()

        fun hideNoReportLayout()

        fun showToast(message: String, imageVisibility: Int, toastPosition: ToastPosition)

        fun openReportDetailPage(patientMedicalReport: PatientMedicalReport)

        fun popFragment()
    }

    interface Presenter : BasePresenter {

        fun fetchMedicalReport()

        fun removeFromDB(patientMedicalReport: PatientMedicalReport, listSize : Int, medicalReportAdapter: MedicalReportAdapter, position: Int)

        fun popFragment()
    }
}