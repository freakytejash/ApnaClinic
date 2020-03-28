package `in`.co.akgroups.apnaClinic.data.source.local.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "MenuItem")
class MenuItem {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    var id: Long = 0L

    @SerializedName("priority")
    @Expose
    var priority: Long = 0L

    @SerializedName("name")
    @Expose
    var name: String = ""

    @SerializedName("picture")
    @Expose
    var picture: String = ""
}