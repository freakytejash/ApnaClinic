package `in`.co.akgroups.apnaClinic.data.source.local.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user_data")
class UserData() : Parcelable {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    var id: Long = 0L


    @SerializedName("email")
    @Expose
    @ColumnInfo
    var email: String? = null

    @SerializedName("loginType")
    @Expose
    @ColumnInfo
    var loginType: Int = 0

    @SerializedName("telephone")
    @Expose
    @ColumnInfo
    var telephone: Long = 0

    @SerializedName("first_name")
    @Expose
    @ColumnInfo
    var first_name: String = ""

    @SerializedName("last_name")
    @Expose
    @ColumnInfo
    var last_name: String = ""

    @SerializedName("picture")
    @Expose
    @ColumnInfo
    var picture: String = ""

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

    @SerializedName("success")
    @Expose
    var success: Boolean = false

    @SerializedName("isVerified")
    @Expose
    var isVerified: Boolean = false

    @SerializedName("isActive")
    @Expose
    var isActive: Boolean = false

    @SerializedName("isLoginOtpVerified")
    @Expose
    var isLoginOtpVerified: Boolean = false

    @SerializedName("qb_id_doc")
    @Expose
    var qb_id_doc: Long = 0L

    @SerializedName("doctorTitle")
    @Expose
    var doctorTitle: String = ""

    @SerializedName("doctorDesc")
    @Expose
    var doctorDesc: String = ""

    @SerializedName("prof_id")
    @Expose
    var prof_id: Int = 0

    @SerializedName("yearsofexperience")
    @Expose
    var yearsofexperience: Int = 0

    @SerializedName("speciality")
    @Expose
    var speciality: String = ""

    @SerializedName("language")
    @Expose
    var language: String = ""

    @SerializedName("medicalboard")
    @Expose
    var medicalboard: String = ""

    @SerializedName("registrationno")
    @Expose
    var registrationno: String = ""

    @SerializedName("graduate")
    @Expose
    var graduate: String = ""

    @SerializedName("postgraduate")
    @Expose
    var postgraduate: String = ""

    @SerializedName("edu_id")
    @Expose
    var edu_id: Int = 0

    constructor(parcel: Parcel) : this() {
        id = parcel.readLong()
        email = parcel.readString()
        loginType = parcel.readInt()
        telephone = parcel.readLong()
        first_name = parcel.readString()
        last_name = parcel.readString()
        picture = parcel.readString()
        weightInKg = parcel.readString()
        bloodGroup = parcel.readString()
        bmi = parcel.readString()
        gender = parcel.readString()
        dob = parcel.readString()
        heightInFeet = parcel.readString()
        primaryAddress = parcel.readString()
        country = parcel.readString()
        state = parcel.readString()
        city = parcel.readString()
        zip = parcel.readString()
        success = parcel.readByte() != 0.toByte()
        isVerified = parcel.readByte() != 0.toByte()
        isActive = parcel.readByte() != 0.toByte()
        isLoginOtpVerified = parcel.readByte() != 0.toByte()
        doctorTitle = parcel.readString()
        doctorDesc = parcel.readString()
        qb_id_doc = parcel.readLong()
        speciality = parcel.readString()
        language = parcel.readString()
        yearsofexperience = parcel.readInt()
        prof_id = parcel.readInt()
        medicalboard = parcel.readString()
        registrationno = parcel.readString()
        graduate = parcel.readString()
        postgraduate = parcel.readString()
        edu_id = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(email)
        parcel.writeInt(loginType)
        parcel.writeLong(telephone)
        parcel.writeString(first_name)
        parcel.writeString(last_name)
        parcel.writeString(picture)
        parcel.writeString(weightInKg)
        parcel.writeString(bloodGroup)
        parcel.writeString(bmi)
        parcel.writeString(gender)
        parcel.writeString(dob)
        parcel.writeString(heightInFeet)
        parcel.writeString(primaryAddress)
        parcel.writeString(country)
        parcel.writeString(state)
        parcel.writeString(city)
        parcel.writeString(zip)
        parcel.writeByte(if (success) 1 else 0)
        parcel.writeByte(if (isVerified) 1 else 0)
        parcel.writeByte(if (isActive) 1 else 0)
        parcel.writeByte(if (isLoginOtpVerified) 1 else 0)
        parcel.writeString(doctorTitle)
        parcel.writeString(doctorDesc)
        parcel.writeLong(qb_id_doc)
        parcel.writeString(speciality)
        parcel.writeString(language)
        parcel.writeInt(yearsofexperience)
        parcel.writeInt(prof_id)
        parcel.writeString(medicalboard)
        parcel.writeString(registrationno)
        parcel.writeString(graduate)
        parcel.writeString(postgraduate)
        parcel.writeInt(edu_id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserData> {
        override fun createFromParcel(parcel: Parcel): UserData {
            return UserData(parcel)
        }

        override fun newArray(size: Int): Array<UserData?> {
            return arrayOfNulls(size)
        }
    }
}