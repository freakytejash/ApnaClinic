package `in`.co.akgroups.apnaClinic.data.source.parser

import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.Dependent
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.PatientMedicalReport
import `in`.co.akgroups.apnaClinic.data.source.remote.responses.DependentsResponse
import `in`.co.akgroups.apnaClinic.data.source.remote.responses.PatientReportResponse


interface IParser {

    fun parseDependentsData(dependentResponse: DependentsResponse): List<Dependent>

    fun parseMedicalReportResponse(patientReportResponse: PatientReportResponse): List<PatientMedicalReport>
}