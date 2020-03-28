package `in`.co.akgroups.apnaClinic.data.source.remote

import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.PatientMedicalReport
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.data.source.remote.requests.*
import `in`.co.akgroups.apnaClinic.data.source.remote.responses.*
import android.support.annotation.VisibleForTesting
import io.reactivex.Completable
import io.reactivex.Single

class AppApiHelper(val apiService: ApiService) : ApiHelper {

    override fun loginUser(loginRequest: LoginRequest): Single<LoginResponse> {
        return apiService.loginUser(loginRequest)
    }

    companion object {
        private var INSTANCE: AppApiHelper? = null

        @JvmStatic
        fun getInstance(apiService: ApiService): AppApiHelper {
            if (INSTANCE == null) {
                synchronized(AppApiHelper::javaClass) {
                    INSTANCE = AppApiHelper(apiService)
                }
            }
            return INSTANCE!!
        }

        @VisibleForTesting
        fun clearInstance() {
            INSTANCE = null
        }
    }

    override fun validateEmail(email: String): Single<ForgotResponse> {
        return apiService.forgotPassword(email)
    }

    override fun registerUser(
            firstName: String,
            lastName: String,
            email: String,
            mobile: Long,
            password: String,
            type: Int
    ): Single<RegisterResponse> {
        return apiService.registerUser(RegisterRequest(firstName, lastName, email, mobile, password, type))
    }

    override fun changePassword(userData: UserData, currentPassword: String, newPassword: String): Single<ChangePasswordResponse> {
        return apiService.changePassword(ChangePasswordRequest(userData.id, currentPassword, newPassword))
    }

    override fun fetchDependentsData(userData: UserData): Single<DependentsResponse> {
        return apiService.fetchDependents(userData.id)
    }

    override fun fetchPatientReport(userData: UserData): Single<PatientReportResponse> {
        return apiService.fetchPatientReport(userData.id)
    }

    override fun removePatientReport(userData: UserData, patientMedicalReport: PatientMedicalReport): Completable {
        return apiService.removeFromPatientReport(userData.id, patientMedicalReport.id)
    }

    override fun verifyOtp(userData: UserData, email_otp: Int, mobile_Otp: Int, verified: Boolean): Single<OtpVerificationResponse> {
        return apiService.verifyOTP(OtpVerificationRequest(userData.id, userData.loginType, email_otp, mobile_Otp, verified))
    }

    override fun verifyLoginOtp(userData: UserData, otp: Int): Single<OtpVerificationResponse> {
        return apiService.verifyLoginOTP(OtpVerificationRequest(userData.id, userData.loginType, true, otp))
    }

    override fun fetchDoctorProfile(userData: UserData): Single<DoctorProfileResponse> {
        return apiService.getDoctorProfile(DoctorProfileRequest(userData.id))
    }

    override fun fetchDoctorExperience(userData: UserData): Single<DoctorExperienceResponse> {
        return apiService.getDoctorExperience(DoctorProfileRequest(userData.id))
    }

    override fun fetchDoctorQualification(userData: UserData): Single<DoctorQualificationResponse> {
        return apiService.getDoctorQualification(DoctorProfileRequest(userData.id))
    }

    override fun updateDoctorQualification(userData: UserData, medicalboard: String, registrationno: String, graduate: String, postgraduate: String): Single<UpdateDoctorQualificationResponse> {
        return apiService.updateDoctorQualification(UpdateDoctorQualificationRequest(userData.id, medicalboard, registrationno, graduate, postgraduate))
    }

    override fun changePasswordCur(userData: UserData, currentPassword: String, newPassword: String): Single<ChangePasswordCurResponse> {
        return apiService.changePasswordCur(ChangePasswordCurRequest(userData.id, userData.loginType, currentPassword, newPassword))
    }

    override fun updateDoctorExperience(userData: UserData, exp: String, speciality: String, language: String): Single<UpdateDoctorQualificationResponse> {
        return apiService.updateDoctorExperience(UpdateDoctorExperienceRequest(userData.id, exp, speciality, language))
    }
}