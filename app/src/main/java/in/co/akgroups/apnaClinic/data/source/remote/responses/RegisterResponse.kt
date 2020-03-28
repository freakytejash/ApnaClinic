package `in`.co.akgroups.apnaClinic.data.source.remote.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RegisterResponse  {

    @SerializedName("status")
    @Expose
    var status: Int? = null

    @SerializedName("IsVerified")
    @Expose
    var IsVerified: Boolean = false

    @SerializedName("success")
    @Expose
    var success: Boolean = false

    @SerializedName("IsActive")
    @Expose
    var IsActive: Boolean = false

    @SerializedName("data")
    @Expose
    var data: Data? = null

    class Data {

        @SerializedName("message")
        @Expose
        var message: String = ""

        @SerializedName("id")
        @Expose
        var id: Long = 0
    }
}