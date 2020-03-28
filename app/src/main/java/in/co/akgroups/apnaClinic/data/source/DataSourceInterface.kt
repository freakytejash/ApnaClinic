package `in`.co.akgroups.apnaClinic.data.source

import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.Dependent
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.PatientMedicalReport
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.data.source.remote.responses.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface DataSourceInterface {

    fun checkIfUserAlreadyLoggedIn(): Boolean

    fun loginUser(loginId: String, password: String, type: Int): Single<UserData>

    fun getUserData(): Single<UserData>

    fun registerUser(firstName: String, lastName: String, email: String, phone: Long,
            password: String, type: Int): Single<RegisterResponse>

    fun validateEmail(email: String): Single<ForgotResponse>

    fun changePassword(userData: UserData, currentPassword: String, newPassword: String): Single<ChangePasswordResponse>

    fun fetchDependentsData(mUserData: UserData): Single<List<Dependent>>

    fun fetchPatientMedicalReport(mUserData: UserData): Single<List<PatientMedicalReport>>

    fun removeFromMedicalReport(userData: UserData, patientMedicalReport: PatientMedicalReport): Completable

    fun verifyOtp(userData: UserData, email_otp: Int, mobile_Otp: Int, verified: Boolean): Single<OtpVerificationResponse>

    fun verifyLoginOtp(userData: UserData, otp: Int): Single<OtpVerificationResponse>

    fun fetchDoctorProfile(userData: UserData): Flowable<UserData>

    fun fetchDoctorExperience(userData: UserData): Flowable<UserData>

    fun fetchDoctorQualification(userData: UserData): Flowable<UserData>

    fun changePasswordCur(userData: UserData, currentPassword: String, newPassword: String): Single<ChangePasswordCurResponse>

    fun updateDoctorQualification(userData: UserData, medicalboard: String,
                                  registrationno: String, graduate: String, postgraduate: String): Single<UpdateDoctorQualificationResponse>

    fun updateDoctorExperience(userData: UserData, exp: String, speciality: String, language: String): Single<UpdateDoctorQualificationResponse>

}