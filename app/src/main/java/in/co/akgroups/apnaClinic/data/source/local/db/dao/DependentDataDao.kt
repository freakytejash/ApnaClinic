package `in`.co.akgroups.apnaClinic.data.source.local.db.dao

import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.Dependent
import android.arch.persistence.room.*

@Dao
interface DependentDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllDependentData(dependentList : List<Dependent>)

    @Query("SELECT * FROM dependent")
    fun getAllDependentData(): List<Dependent>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateDependentData(dependent: Dependent)

    @Query("DELETE FROM user_data")
    fun deleteUserData()

    @Query("DELETE FROM dependent WHERE id = :dependentId")
    fun deleteDependent(dependentId: Long)
}