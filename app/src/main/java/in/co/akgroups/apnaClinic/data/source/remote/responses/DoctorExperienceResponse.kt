package `in`.co.akgroups.apnaClinic.data.source.remote.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by narendrapal on 19/01/2020
 */

class DoctorExperienceResponse {

    @SerializedName("ExperienceDetail")
    @Expose
    var experienceDetail: List<ExperienceDetail>? = null
    @SerializedName("msg")
    @Expose
    var msg: String? = null
    @SerializedName("status")
    @Expose
    var status: Boolean? = null


    class ExperienceDetail {
        @SerializedName("prof_id")
        @Expose
        var profId: Int = 0
        @SerializedName("doctor_id")
        @Expose
        var doctorId: String? = null
        @SerializedName("yearsofexperience")
        @Expose
        var yearsofexperience: Int = 0
        @SerializedName("speciality")
        @Expose
        var speciality: String = ""
        @SerializedName("language")
        @Expose
        var language: String = ""
    }
}
