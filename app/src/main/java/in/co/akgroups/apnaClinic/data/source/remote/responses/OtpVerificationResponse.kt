package `in`.co.akgroups.apnaClinic.data.source.remote.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by narendrapal on 15/01/2020
 */

class OtpVerificationResponse {

    @SerializedName("status")
    @Expose
    var status: Int = 0

    @SerializedName("success")
    @Expose
    var success: Boolean = false

    @SerializedName("msg")
    @Expose
    var msg: String = ""

    @SerializedName("IsVerified")
    @Expose
    var isVerified: Boolean = false

    @SerializedName("IsActive")
    @Expose
    var isActive: Boolean = false
}