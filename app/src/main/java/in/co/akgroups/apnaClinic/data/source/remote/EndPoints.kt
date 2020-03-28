package `in`.co.akgroups.apnaClinic.data.source.remote

object EndPoints {

    /****************** RELATED TO USER LOGIN ***********************/


    const val USER_LOGIN = "Login"
    const val USER_REGISTER = "SignUp"
    const val OTP = "Otp"
    const val FORGOT_PASSWORD = "user/forgotPassword/?format=json"
    const val CHANGE_PASSWORD = "user/changePassword/?format=json"
    const val USER_DEPENDENDS = "user/dependents/?format=json"
    const val PATIENT_REPORT = "user/report/?format=json"
    const val REMOVE_FROM_PATIENT_REPORT = "user/report/delete/{id}"
    const val GET_DOCTOR_PROFILE = "doctor/Get_Profile"
    const val GET_DOCTOR_EXPERIENCE = "doctor/Get_ExperienceDetail"
    const val GET_DOCTOR_QUALIFICATION = "doctor/Get_QualificationDetail"
    const val CHANGE_PASSWORD_CUR = "ChangePasswordCur.php"
    const val UPDATE_DOCTOR_QUALIFICATION = "doctor/doctor_qualification"
    const val UPDATE_DOCTOR_EXPERIENCE= "doctor/doctor_experience"
}