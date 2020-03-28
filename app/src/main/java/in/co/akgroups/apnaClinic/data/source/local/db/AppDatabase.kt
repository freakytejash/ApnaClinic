package `in`.co.akgroups.apnaClinic.data.source.local.db

import `in`.co.akgroups.apnaClinic.data.source.local.db.converters.GenreConverter
import `in`.co.akgroups.apnaClinic.data.source.local.db.converters.ImageConverter
import `in`.co.akgroups.apnaClinic.data.source.local.db.dao.DependentDataDao
import `in`.co.akgroups.apnaClinic.data.source.local.db.dao.MedicalReportDao
import `in`.co.akgroups.apnaClinic.data.source.local.db.dao.UserDataDao
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.Dependent
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.PatientMedicalReport
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import `in`.co.akgroups.apnaClinic.data.source.local.entity.Genre
import `in`.co.akgroups.apnaClinic.utils.Const
import `in`.co.akgroups.apnaClinic.utils.Const.DATABASE_VERSION
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context


@Database(
    entities = arrayOf(
        UserData::class,
        Dependent::class,
        PatientMedicalReport::class
    ), version = DATABASE_VERSION, exportSchema = true
)
@TypeConverters(
    ImageConverter::class
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDataDao(): UserDataDao

    abstract fun dependentDataDao(): DependentDataDao

    abstract fun medicalReportDao(): MedicalReportDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        private val lock = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, Const.DB_NAME
                    ).build()
                }
                return INSTANCE!!
            }
        }
    }
}