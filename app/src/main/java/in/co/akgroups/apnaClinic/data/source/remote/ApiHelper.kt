package `in`.co.akgroups.apnaClinic.data.source.remote

import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.PatientMedicalReport
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.data.source.remote.requests.LoginRequest
import `in`.co.akgroups.apnaClinic.data.source.remote.responses.*
import io.reactivex.Completable
import io.reactivex.Single

interface ApiHelper {

    /****************** RELATED TO USER ONBOARDING ***********************/

    fun loginUser(loginRequest: LoginRequest): Single<LoginResponse>

    fun validateEmail(email: String): Single<ForgotResponse>

    fun registerUser(
            firstName: String,
            lastName: String,
            email: String,
            mobile: Long,
            password: String,
            type: Int
    ): Single<RegisterResponse>

    fun changePassword(userData: UserData, currentPassword: String, newPassword: String): Single<ChangePasswordResponse>

    fun fetchDependentsData(userData: UserData): Single<DependentsResponse>

    fun fetchPatientReport(userData: UserData): Single<PatientReportResponse>

    fun removePatientReport(userData: UserData, patientMedicalReport: PatientMedicalReport): Completable

    fun verifyOtp(userData: UserData, email_otp: Int, mobile_Otp: Int, verified: Boolean): Single<OtpVerificationResponse>

    fun verifyLoginOtp(userData: UserData, otp: Int): Single<OtpVerificationResponse>

    fun fetchDoctorProfile(userData: UserData): Single<DoctorProfileResponse>

    fun fetchDoctorExperience(userData: UserData): Single<DoctorExperienceResponse>

    fun fetchDoctorQualification(userData: UserData): Single<DoctorQualificationResponse>

    fun changePasswordCur(userData: UserData, currentPassword: String, newPassword: String): Single<ChangePasswordCurResponse>

    fun updateDoctorQualification(userData: UserData, medicalboard: String, registrationno: String,
                                  graduate: String, postgraduate: String): Single<UpdateDoctorQualificationResponse>

    fun updateDoctorExperience(userData: UserData, exp: String, speciality: String, language: String)
                                : Single<UpdateDoctorQualificationResponse>
}