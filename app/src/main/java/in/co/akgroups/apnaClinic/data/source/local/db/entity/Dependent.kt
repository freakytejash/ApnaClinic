package `in`.co.akgroups.apnaClinic.data.source.local.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "dependent")
class Dependent {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    var id: Long = 0

    @SerializedName("email")
    @Expose
    @ColumnInfo
    var email: String? = null

    @SerializedName("first_name")
    @Expose
    var first_name: String? = null

    @SerializedName("last_name")
    @Expose
    var last_name: String? = null

    @SerializedName("profilePic")
    @Expose
    var profilePic: String? = null

    @SerializedName("telephone")
    @Expose
    var telephone: Long? = null

    @SerializedName("weight_in_kg")
    @Expose
    var weightInKg: String? = null

    @SerializedName("blood_group")
    @Expose
    var bloodGroup: String? = null

    @SerializedName("bmi")
    @Expose
    var bmi: String? = null

    @SerializedName("gender")
    @Expose
    var gender: String? = null

    @SerializedName("dob")
    @Expose
    var dob: String? = null

    @SerializedName("height_in_feet")
    @Expose
    var heightInFeet: String? = null

    @SerializedName("primary_address")
    @Expose
    var primaryAddress: String? = null

    @SerializedName("country")
    @Expose
    var country: String? = null

    @SerializedName("state")
    @Expose
    var state: String? = null

    @SerializedName("city")
    @Expose
    var city: String? = null

    @SerializedName("zip")
    @Expose
    var zip: String? = null

    @SerializedName("relationship")
    @Expose
    var relationship: String? = null
}