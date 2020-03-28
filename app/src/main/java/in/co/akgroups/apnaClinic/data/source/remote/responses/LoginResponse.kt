package `in`.co.akgroups.apnaClinic.data.source.remote.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class LoginResponse {
    @SerializedName("status")
    @Expose
    var status: Int? = null

    @SerializedName("IsVerified")
    @Expose
    var isVerified: Boolean = false

    @SerializedName("IsActive")
    @Expose
    var IsActive: Boolean = false

    @SerializedName("success")
    @Expose
    var success: Boolean = false

    @SerializedName("msg")
    @Expose
    var msg: String = ""

    @SerializedName("data")
    @Expose
    var data: Data? = null


    class Data {

        @SerializedName("Id")
        @Expose
        var id: Long = 0L

        @SerializedName("type")
        @Expose
        var type: Int = 0

        @SerializedName("first_name")
        @Expose
        var first_name: String = ""

        @SerializedName("last_name")
        @Expose
        var last_name: String = ""

        @SerializedName("profilePic")
        @Expose
        var profilePic: String? = null

        @SerializedName("telephone")
        @Expose
        var telephone: Long = 0L

    }
}