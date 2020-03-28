package `in`.co.akgroups.apnaClinic.data.source.local.db

import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.Dependent
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.PatientMedicalReport
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import io.reactivex.Completable
import io.reactivex.Single


interface DBHelper {

    fun insertloginData(userData: UserData): Single<Long>

    fun getUserData(id : Long): Single<UserData>

    fun getLoggedInUser() : Single<UserData>

    fun insertDependentData(dependentList: List<Dependent>): Completable

    fun getDependentData(): Single<List<Dependent>>

    fun insertMedicalReport(medicalReportList: List<PatientMedicalReport>): Completable

    fun getMedicalReport(): Single<List<PatientMedicalReport>>

    fun removeFromPatientMedicalReport(id: Long): Completable
}