package `in`.co.akgroups.apnaClinic.data.source.local.db.dao

import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.PatientMedicalReport
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface MedicalReportDao  {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllReport(patientMedicalReportList:List<PatientMedicalReport>)

    @Query("SELECT * FROM patient_report")
    fun getAllReport(): List<PatientMedicalReport>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateDependentData(patientMedicalReport: PatientMedicalReport)

    @Query("DELETE FROM patient_report")
    fun deleteAllReport()

    @Query("DELETE FROM patient_report WHERE id = :id")
    fun deleteReport(id: Long)
}