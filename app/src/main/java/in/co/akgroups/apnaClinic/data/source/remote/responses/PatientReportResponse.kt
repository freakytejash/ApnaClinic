package `in`.co.akgroups.apnaClinic.data.source.remote.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class PatientReportResponse {


    @SerializedName("status")
    @Expose
    var status: Int? = null
    @SerializedName("data")
    @Expose
    var data: Data? = null

    class Data {

        @SerializedName("details")
        @Expose
        var details: List<Detail>? = null

    }

    class Detail {

        @SerializedName("id")
        @Expose
        var id: Long = 0L
        @SerializedName("report_name")
        @Expose
        var reportName: String? = null
        @SerializedName("lab_name")
        @Expose
        var labName: String? = null
        @SerializedName("date")
        @Expose
        var date: String? = null
        @SerializedName("attachments")
        @Expose
        var attachments: List<Attachment>? = null

    }

    class Attachment {

        @SerializedName("name")
        @Expose
        var name: String? = null
        @SerializedName("link")
        @Expose
        var link: String? = null

    }
}