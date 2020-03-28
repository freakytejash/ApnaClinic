package `in`.co.akgroups.apnaClinic.data.source

import `in`.co.akgroups.apnaClinic.BuildConfig
import `in`.co.akgroups.apnaClinic.data.source.local.db.DBHelper
import `in`.co.akgroups.apnaClinic.data.source.local.db.RoomDBHelper
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.Dependent
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.PatientMedicalReport
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.data.source.local.prefs.PreferenceHelper
import `in`.co.akgroups.apnaClinic.data.source.parser.IParser
import `in`.co.akgroups.apnaClinic.data.source.remote.ApiHelper
import `in`.co.akgroups.apnaClinic.data.source.remote.AppApiHelper
import `in`.co.akgroups.apnaClinic.data.source.remote.requests.LoginRequest
import `in`.co.akgroups.apnaClinic.data.source.remote.responses.*
import `in`.co.akgroups.apnaClinic.utils.Const
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class DataRepository(
        val dbHelper: DBHelper,
        val apiHelper: ApiHelper,
        val parser: IParser,
        val preferenceHelper: PreferenceHelper
) : DataSourceInterface {

    companion object {

        private var INSTANCE: DataRepository? = null

        /**
         * Returns the single instance of this class, creating it if necessary.
         */
        @JvmStatic
        fun getInstance(
                roomDBHelper: RoomDBHelper,
                appApiHelper: AppApiHelper,
                parser: IParser,
                preferenceHelper: PreferenceHelper
        ): DataRepository {
            return INSTANCE ?: DataRepository(roomDBHelper, appApiHelper, parser, preferenceHelper)
                    .apply { INSTANCE = this }
        }

        /**
         * Used to force [getInstance] to create a new instance
         * next time it's called.
         */
        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }

    override fun checkIfUserAlreadyLoggedIn(): Boolean {
        return false
    }

    override fun loginUser(loginId: String, password: String, type: Int): Single<UserData> {
        val userProfileRequest =
                LoginRequest.UserProfileRequest(Const.LOGIN_TYPE, BuildConfig.VERSION_CODE.toString())
        val loginRequest = LoginRequest(loginId, password, userProfileRequest, type)
        return apiHelper.loginUser(loginRequest)
                .flatMap { res ->
                    val userData = UserData()
                    if (res.data != null) {
                        userData.email = loginId
                        userData.telephone = res.data!!.telephone
                        userData.id = res.data!!.id
                        userData.loginType = res.data!!.type
                        userData.first_name = res.data!!.first_name
                        userData.last_name = res.data!!.last_name
                        userData.picture = res.data!!.profilePic ?: ""
                        userData.success = res.success
                        userData.isActive = res.IsActive
                        userData.isVerified = res.isVerified
                    }
                    dbHelper.insertloginData(userData)
                    dbHelper.getUserData((userData.id))
                }
//            .doOnError {
//                it
//            }
//            .onErrorReturn {
//                val userData = UserData()
//                userData.email = "abcs@gmail.com"
//                userData.id = 123
//                if(Utils.isDoctor){
//                    userData.loginType = 1
//                } else {
//                    userData.loginType = 2
//                }
//                userData.name = "Narendra"
//                userData.picture = "https://graph.facebook.com/2186275741429777/picture?type=large"
//                dbHelper.insertloginData(userData)
//                userData
//            }
    }

    override fun getUserData(): Single<UserData> {
        return dbHelper.getLoggedInUser()
    }

    override fun registerUser(
            firstName: String,
            lastName: String,
            email: String,
            phone: Long,
            password: String,
            type: Int
    ): Single<RegisterResponse> {
        return apiHelper.registerUser(firstName, lastName, email, phone, password, type)
    }

    override fun validateEmail(email: String): Single<ForgotResponse> {
        return apiHelper.validateEmail(email)
    }

    override fun changePassword(
            userData: UserData,
            currentPassword: String,
            newPassword: String
    ): Single<ChangePasswordResponse> {
        return apiHelper.changePassword(userData, currentPassword, newPassword)
    }

    override fun changePasswordCur(userData: UserData,
                                   currentPassword: String,
                                   newPassword: String): Single<ChangePasswordCurResponse> {
        return apiHelper.changePasswordCur(userData, currentPassword, newPassword)
    }

    override fun fetchDependentsData(mUserData: UserData): Single<List<Dependent>> {
        return apiHelper.fetchDependentsData(mUserData)
                .flatMap {
                    val dependentList = parser.parseDependentsData(it)
                    dbHelper.insertDependentData(dependentList)
                    dbHelper.getDependentData()
                }
                .onErrorReturn {
                    ArrayList()
                }
                .flatMap {
                    Single.just(it)
                }
    }

    override fun fetchPatientMedicalReport(mUserData: UserData): Single<List<PatientMedicalReport>> {
        return apiHelper.fetchPatientReport(mUserData)
                .flatMap {
                    val patientMedicalReport = parser.parseMedicalReportResponse(it)
                    dbHelper.insertMedicalReport(patientMedicalReport)
                    dbHelper.getMedicalReport()
                }
                .onErrorReturn {
                    ArrayList()
                }
                .flatMap {
                    Single.just(it)
                }
    }

    override fun removeFromMedicalReport(
            userData: UserData,
            patientMedicalReport: PatientMedicalReport
    ): Completable {
        return apiHelper.removePatientReport(userData, patientMedicalReport)
                .doOnComplete {
                    dbHelper.removeFromPatientMedicalReport(patientMedicalReport.id)
                }
    }

    override fun verifyOtp(
            userData: UserData,
            email_otp: Int,
            mobile_Otp: Int,
            verified: Boolean
    ): Single<OtpVerificationResponse> {
        return apiHelper.verifyOtp(userData, email_otp, mobile_Otp, verified)
    }

    override fun updateDoctorQualification(userData: UserData, medicalboard: String,
                                           registrationno: String, graduate: String,
                                           postgraduate: String): Single<UpdateDoctorQualificationResponse> {
        return apiHelper.updateDoctorQualification(userData, medicalboard, registrationno, graduate, postgraduate)
    }

    override fun updateDoctorExperience(userData: UserData, exp: String, speciality: String, language: String):
            Single<UpdateDoctorQualificationResponse> {
        return apiHelper.updateDoctorExperience(userData, exp, speciality, language)
    }

    override fun verifyLoginOtp(userData: UserData, otp: Int): Single<OtpVerificationResponse> {
        return apiHelper.verifyLoginOtp(userData, otp)
                .doOnSuccess {
                    userData.isActive = true
                    if (it.success) {
                        userData.isLoginOtpVerified = true
                    }
                    userData.isVerified = true
                    userData.success = it.success
                    dbHelper.insertloginData(userData)
                }
    }

    override fun fetchDoctorProfile(userData: UserData): Flowable<UserData> {
        val doctorProfileFromDb = dbHelper.getUserData(userData.id)
        val doctorProfileFromWeb = apiHelper.fetchDoctorProfile(userData)
                .flatMap {
                    if (it.profileDetail != null && it.profileDetail!!.isNotEmpty()) {
                        val profileDetail = it.profileDetail!![0]
                        userData.picture = profileDetail.profilePic.toString()
                        userData.first_name = profileDetail.firstName.toString()
                        userData.last_name = profileDetail.lastName.toString()
                        userData.qb_id_doc = profileDetail.qbIdDoc
                        userData.email = profileDetail.email
                        userData.telephone = profileDetail.telephone
                        userData.doctorTitle = profileDetail.title.toString()
                        userData.doctorDesc = profileDetail.description.toString()
                        userData.city = profileDetail.city
                        userData.country = profileDetail.country
                        userData.state = profileDetail.state
                        dbHelper.insertloginData(userData)
                    }
                    Single.just(userData)
                }
        return Single.concat(doctorProfileFromDb, doctorProfileFromWeb)
    }

    override fun fetchDoctorExperience(userData: UserData): Flowable<UserData> {

        val doctorExpFromDb = dbHelper.getUserData(userData.id)
        val doctorExpFromWeb = apiHelper.fetchDoctorExperience(userData)
                .flatMap {
                    if (it.experienceDetail != null && it.experienceDetail!!.isNotEmpty()) {
                        val experienceDetail = it.experienceDetail!![0]
                        userData.prof_id = experienceDetail.profId
                        userData.yearsofexperience = experienceDetail.yearsofexperience
                        userData.speciality = experienceDetail.speciality
                        userData.language = experienceDetail.language
                        dbHelper.insertloginData(userData)
                    }
                    Single.just(userData)
                }

        return Single.concat(doctorExpFromDb, doctorExpFromWeb)
    }

    override fun fetchDoctorQualification(userData: UserData): Flowable<UserData> {
        val doctorQualificationFromDb = dbHelper.getUserData(userData.id)
                .flatMap {
                    Single.just(it)
                }
        val doctorQualificationFromWeb = apiHelper.fetchDoctorQualification(userData)
                .flatMap {
                    if (it.qualificationDetail != null && it.qualificationDetail!!.isNotEmpty()) {
                        val qualiifiacationDetail = it.qualificationDetail!![0]
                        userData.graduate = qualiifiacationDetail.graduate ?: ""
                        userData.postgraduate = qualiifiacationDetail.postgraduate ?: ""
                        userData.edu_id = qualiifiacationDetail.eduId ?: 0
                        userData.medicalboard = qualiifiacationDetail.medicalboard ?: ""
                        userData.registrationno = qualiifiacationDetail.registrationno ?: ""
                    }
                    Single.just(userData)
                }
        return Single.concat(doctorQualificationFromDb, doctorQualificationFromWeb)
    }
}