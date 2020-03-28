package `in`.co.akgroups.apnaClinic.data.source.remote.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by narendrapal on 19/01/2020
 */

class DoctorProfileResponse {
    @SerializedName("ProfileDetail")
    @Expose
    var profileDetail: List<ProfileDetail>? = null
    @SerializedName("msg")
    @Expose
    var msg: String? = null
    @SerializedName("status")
    @Expose
    var status: Boolean? = null

    class ProfileDetail {
        @SerializedName("doctor_id")
        @Expose
        var doctorId: String? = null
        @SerializedName("qb_id_doc")
        @Expose
        var qbIdDoc: Long = 0L
        @SerializedName("email")
        @Expose
        var email: String? = null
        @SerializedName("telephone")
        @Expose
        var telephone: Long = 0L
        @SerializedName("title")
        @Expose
        var title: String? = null
        @SerializedName("description")
        @Expose
        var description: String? = null
        @SerializedName("first_name")
        @Expose
        var firstName: String? = null
        @SerializedName("last_name")
        @Expose
        var lastName: String? = null
        @SerializedName("gender")
        @Expose
        var gender: String? = null
        @SerializedName("country")
        @Expose
        var country: String? = null
        @SerializedName("state")
        @Expose
        var state: String? = null
        @SerializedName("city")
        @Expose
        var city: String? = null
        @SerializedName("profilePic")
        @Expose
        var profilePic: String?  = null
    }
}