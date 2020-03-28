package `in`.co.akgroups.apnaClinic.data.source.local.db.converters

import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.PatientMedicalReport
import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ImageConverter {

    @TypeConverter
    fun fromString(value: String): List<PatientMedicalReport.Attachments> {
        val listType = object : TypeToken<List<PatientMedicalReport.Attachments>>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromAttachmentList(attachmentList : List<PatientMedicalReport.Attachments>): String {
        val gson = Gson()
        val json = gson.toJson(attachmentList)
        return json
    }
}