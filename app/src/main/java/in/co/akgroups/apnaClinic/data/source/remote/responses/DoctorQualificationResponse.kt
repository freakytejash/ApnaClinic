package `in`.co.akgroups.apnaClinic.data.source.remote.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by narendrapal on 19/01/2020
 */

class DoctorQualificationResponse {

    @SerializedName("QualificationDetail")
    @Expose
    var qualificationDetail: List<QualificationDetail>? = null
    @SerializedName("msg")
    @Expose
    var msg: String? = null
    @SerializedName("status")
    @Expose
    var status: Boolean? = null

    class QualificationDetail {
        @SerializedName("edu_id")
        @Expose
        var eduId: Int? = null
        @SerializedName("doctor_id")
        @Expose
        var doctorId: String? = null
        @SerializedName("medicalboard")
        @Expose
        var medicalboard: String? = null
        @SerializedName("registrationno")
        @Expose
        var registrationno: String? = null
        @SerializedName("graduate")
        @Expose
        var graduate: String? = null
        @SerializedName("postgraduate")
        @Expose
        var postgraduate: String? = null
    }
}