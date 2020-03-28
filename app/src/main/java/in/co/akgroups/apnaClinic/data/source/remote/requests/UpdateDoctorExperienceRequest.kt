package `in`.co.akgroups.apnaClinic.data.source.remote.requests

/**
 * Created by amitacharya on 23/2/20.
 */
class UpdateDoctorExperienceRequest {

    private var doctor_id: Long
    private var exp: String
    private var speciality: String
    private var language: String

    constructor(doctor_id: Long, exp: String, speciality: String, language: String) {
        this.doctor_id = doctor_id
        this.exp = exp
        this.speciality = speciality
        this.language = language
    }
}