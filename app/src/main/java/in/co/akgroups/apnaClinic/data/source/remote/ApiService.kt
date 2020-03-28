package `in`.co.akgroups.apnaClinic.data.source.remote

import `in`.co.akgroups.apnaClinic.BuildConfig
import `in`.co.akgroups.apnaClinic.data.source.remote.requests.*
import `in`.co.akgroups.apnaClinic.data.source.remote.responses.*
import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface ApiService {

    companion object Factory {

        const val HEADER_AUTHORIZATION = "Authorization"
        const val USER_ID = "user_id"

        private val okHttpClient = OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
//            .build()


        fun create(): ApiService {

            if (BuildConfig.DEBUG) {
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                okHttpClient.addInterceptor(loggingInterceptor)
            }
            val retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient.build())
                    .build()

            return retrofit.create(ApiService::class.java)
        }
    }


    @POST(EndPoints.USER_LOGIN)
    fun loginUser(@Body loginRequest: LoginRequest): Single<LoginResponse>

    @POST(EndPoints.FORGOT_PASSWORD)
    fun forgotPassword(@Body email: String): Single<ForgotResponse>

    @POST(EndPoints.USER_REGISTER)
    fun registerUser(@Body registerRequest: RegisterRequest): Single<RegisterResponse>

    @POST(EndPoints.OTP)
    fun verifyOTP(@Body otpVerificationRequest: OtpVerificationRequest): Single<OtpVerificationResponse>

    @POST(EndPoints.OTP)
    fun verifyLoginOTP(@Body otpVerificationRequest: OtpVerificationRequest): Single<OtpVerificationResponse>

    @POST(EndPoints.CHANGE_PASSWORD)
    fun changePassword(@Body changePasswordRequest: ChangePasswordRequest): Single<ChangePasswordResponse>

    @POST(EndPoints.USER_DEPENDENDS)
    fun fetchDependents(@Body userId: Long): Single<DependentsResponse>

    @POST(EndPoints.PATIENT_REPORT)
    fun fetchPatientReport(@Body userId: Long): Single<PatientReportResponse>

    @DELETE(EndPoints.REMOVE_FROM_PATIENT_REPORT)
    fun removeFromPatientReport(@Header(USER_ID) userId: Long, @Path("id") id: Long): Completable

    @POST(EndPoints.GET_DOCTOR_PROFILE)
    fun getDoctorProfile(@Body doctorProfileRequest: DoctorProfileRequest): Single<DoctorProfileResponse>

    @POST(EndPoints.GET_DOCTOR_EXPERIENCE)
    fun getDoctorExperience(@Body doctorProfileRequest: DoctorProfileRequest): Single<DoctorExperienceResponse>

    @POST(EndPoints.GET_DOCTOR_QUALIFICATION)
    fun getDoctorQualification(@Body doctorProfileRequest: DoctorProfileRequest): Single<DoctorQualificationResponse>

    @POST(EndPoints.UPDATE_DOCTOR_QUALIFICATION)
    fun updateDoctorQualification(@Body doctorProfileRequest: UpdateDoctorQualificationRequest): Single<UpdateDoctorQualificationResponse>

    @POST(EndPoints.UPDATE_DOCTOR_EXPERIENCE)
    fun updateDoctorExperience(@Body doctorProfileRequest: UpdateDoctorExperienceRequest): Single<UpdateDoctorQualificationResponse>

    @POST(EndPoints.CHANGE_PASSWORD_CUR)
    fun changePasswordCur(@Body changePasswordCurRequest: ChangePasswordCurRequest): Single<ChangePasswordCurResponse>
}