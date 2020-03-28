package `in`.co.akgroups.apnaClinic.data.source.local.db.converters

import `in`.co.akgroups.apnaClinic.data.source.local.entity.Genre
import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreConverter {

    @TypeConverter
    fun fromString(value: String): List<Genre> {
        val listType = object : TypeToken<List<Genre>>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromGenreList(genreList : List<Genre>): String {
        val gson = Gson()
        val json = gson.toJson(genreList)
        return json
    }

}