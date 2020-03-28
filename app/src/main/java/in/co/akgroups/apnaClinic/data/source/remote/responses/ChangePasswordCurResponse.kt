package `in`.co.akgroups.apnaClinic.data.source.remote.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by amitacharya on 3/2/20.
 */
class ChangePasswordCurResponse {

    @SerializedName("status")
    @Expose
    var status: Int? = null

    @SerializedName("data")
    @Expose
    var data: Data? = null

    class Data {

        @SerializedName("message")
        @Expose
        var message: String = ""

        @SerializedName("success")
        @Expose
        var success: String = ""
    }
}