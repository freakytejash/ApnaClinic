package `in`.co.akgroups.apnaClinic.data.source.parser

import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.Dependent
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.PatientMedicalReport
import `in`.co.akgroups.apnaClinic.data.source.remote.responses.DependentsResponse
import `in`.co.akgroups.apnaClinic.data.source.remote.responses.PatientReportResponse
import android.support.annotation.VisibleForTesting

class AppParser : IParser {

    companion object {
        private var INSTANCE: IParser? = null

        @JvmStatic
        fun getInstance(): IParser {
            if (INSTANCE == null) {
                synchronized(AppParser::javaClass) {
                    INSTANCE = AppParser()
                }
            }
            return INSTANCE!!
        }

        @VisibleForTesting
        fun clearInstance() {
            INSTANCE = null
        }
    }

    override fun parseDependentsData(dependentResponse: DependentsResponse): List<Dependent> {
        val data = dependentResponse.data
        val dependentList = ArrayList<Dependent>()
        if(data != null){
            val details = data.details
            if(details != null && details.isNotEmpty()){
                for(detail in details){
                    val dependent = Dependent()
                    dependent.id = detail.id ?: 0
                    dependent.bmi = detail.bmi
                    dependent.bloodGroup = detail.bloodGroup
                    dependent.city = detail.city
                    dependent.country = detail.country
                    dependent.dob = detail.dob
                    dependent.gender = detail.gender
                    dependent.heightInFeet = detail.heightInFeet
                    dependent.first_name = detail.first_name
                    dependent.last_name = detail.last_name
                    dependent.primaryAddress = detail.primaryAddress
                    dependent.profilePic = detail.profilePic
                    dependent.relationship = detail.relationship
                    dependent.state = detail.state
                    dependent.telephone = detail.telephone
                    dependent.weightInKg = detail.weightInKg
                    dependent.zip = detail.zip
                    dependentList.add(dependent)
                }
            }

        }
        return  dependentList
    }

    override fun parseMedicalReportResponse(patientReportResponse: PatientReportResponse): List<PatientMedicalReport> {
        val data = patientReportResponse.data
        val patientMedicalReportList = ArrayList<PatientMedicalReport>()
        if(data != null){
            val details = data.details
            if(details != null && details.isNotEmpty()){
                for(detail in details){
                    val patientMedicalReport = PatientMedicalReport()
                    patientMedicalReport.id = detail.id
                    patientMedicalReport.lab_name = detail.labName
                    patientMedicalReport.report_name = detail.reportName
                    patientMedicalReport.report_date = detail.date
                    val attachmentsList = ArrayList<PatientMedicalReport.Attachments>()
                    val responseAttachments = detail.attachments
                    if(responseAttachments != null && responseAttachments.isNotEmpty()){
                        for(responseAttachment in responseAttachments){
                            val attachment = PatientMedicalReport.Attachments()
                            attachment.name = responseAttachment.name
                            attachment.imageUrl = responseAttachment.link
                            attachmentsList.add(attachment)
                        }

                    }
                    patientMedicalReport.attachmentList = attachmentsList
                    patientMedicalReportList.add(patientMedicalReport)
                }
            }
        }
        return patientMedicalReportList
    }
}