package `in`.co.akgroups.apnaClinic.data.source.remote.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ForgotResponse  {

    @SerializedName("status")
    @Expose
    var status: Int? = null
    @SerializedName("data")
    @Expose
    var data: Data? = null

    class Data {

        @SerializedName("success")
        @Expose
        var success: Boolean = false
        @SerializedName("message")
        @Expose
        var message: String = ""
    }
}