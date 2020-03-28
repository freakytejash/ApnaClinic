package `in`.co.akgroups.apnaClinic.data.source.local.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



@Entity(tableName = "genre")
class Genre {

    var isSelected = false

    @PrimaryKey
    @SerializedName("id")
    @Expose
    var id : Long = 0

    @SerializedName("name")
    @Expose
    @ColumnInfo
    var name : String = ""

    @SerializedName("thumbnail")
    @Expose
    @ColumnInfo
    var thumbnailUrl : String = ""


    override fun equals(other: Any?): Boolean {
        if (other is Genre) {
            return this.id == other.id
        }
        return super.equals(other)
    }

    override fun hashCode(): Int {
        val prime = 59
        var result = 1
        val temp1 = this.id
        result = result * prime + if (this.name == null) 43 else this.name.hashCode()
        result = result * prime + (temp1 xor temp1.ushr(32)).toInt()
        return result
    }
}