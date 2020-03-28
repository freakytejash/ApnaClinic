package `in`.co.akgroups.apnaClinic.data.source.remote.requests

/**
 * Created by amitacharya on 18/2/20.
 */
class UpdateDoctorQualificationRequest {

    private var doctor_id: Long

    private var medicalboard: String
    private var registrationno: String
    private var graduate: String
    private var postgraduate: String

    constructor(doctor_id: Long, medicalboard: String, registrationno: String, graduate: String, postgraduate: String) {
        this.doctor_id = doctor_id
        this.medicalboard = medicalboard
        this.registrationno = registrationno
        this.graduate = graduate
        this.postgraduate = postgraduate
    }
}