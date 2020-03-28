package `in`.co.akgroups.apnaClinic.data.source.local.db

import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.Dependent
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.PatientMedicalReport
import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import android.support.annotation.VisibleForTesting
import io.reactivex.Completable
import io.reactivex.Single


class RoomDBHelper private constructor(val database: AppDatabase) : DBHelper {

    override fun getUserData(id: Long): Single<UserData> {
        return Single.fromCallable{database.userDataDao().getUserData(id)}
//        return Single.just(database.userDataDao().getUserData(id))
    }

    override fun getLoggedInUser(): Single<UserData> {
        return Single.fromCallable{database.userDataDao().getLoggedInUser()}
    }

    companion object {
        private var INSTANCE: RoomDBHelper? = null

        @JvmStatic
        fun getInstance(database: AppDatabase): RoomDBHelper {
            if (INSTANCE == null) {
                synchronized(RoomDBHelper::javaClass) {
                    INSTANCE =
                        RoomDBHelper(database)
                }
            }
            return INSTANCE!!
        }

        @VisibleForTesting
        fun clearInstance() {
            INSTANCE = null
        }
    }

    override fun insertDependentData(dependentList: List<Dependent>): Completable {
        return Single.just(database.dependentDataDao().insertAllDependentData(dependentList))
            .toCompletable()
    }

    override fun getDependentData(): Single<List<Dependent>> {
        return Single.fromCallable { database.dependentDataDao().getAllDependentData() }
    }

    override fun insertloginData(userData: UserData): Single<Long> {
        return Single.just(database.userDataDao().insertUserData(userData))
    }

    override fun insertMedicalReport(medicalReportList: List<PatientMedicalReport>): Completable {
        return Single.just(database.medicalReportDao().insertAllReport(medicalReportList))
            .toCompletable()
    }

    override fun getMedicalReport(): Single<List<PatientMedicalReport>> {
        return Single.fromCallable { database.medicalReportDao().getAllReport() }
    }

    override fun removeFromPatientMedicalReport(id: Long): Completable {
        return Single.just(database.medicalReportDao().deleteReport(id)).toCompletable()
    }

}