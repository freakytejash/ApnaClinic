package `in`.co.akgroups.apnaClinic.data.source.local.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "patient_report", indices = arrayOf(Index(value = ["report_name", "lab_name", "report_date"], unique = true)))

class PatientMedicalReport {

    @SerializedName("id")
    @Expose
    @PrimaryKey
    var id: Long = 0

    @SerializedName("report_name")
    @Expose
    @ColumnInfo
    var report_name: String? = null

    @SerializedName("lab_name")
    @Expose
    @ColumnInfo
    var lab_name: String? = null

    @SerializedName("report_date")
    @Expose
    @ColumnInfo
    var report_date: String? = null

    @SerializedName("attachmentList")
    @Expose
    @ColumnInfo
    var attachmentList: List<Attachments>? = null


    class Attachments {
        @SerializedName("name")
        @Expose
        @ColumnInfo
        var name: String? = null

        @SerializedName("imageUrl")
        @Expose
//        @ColumnInfo (typeAffinity = ColumnInfo.BLOB)
        @ColumnInfo
        var imageUrl: String? = null
    }

}