package `in`.co.akgroups.apnaClinic.data.source.remote.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by amitacharya on 18/2/20.
 */
class UpdateDoctorQualificationResponse {

    @SerializedName("msg")
    @Expose
    var msg: String? = null
    @SerializedName("status")
    @Expose
    var status: Boolean? = null
}