package `in`.co.akgroups.apnaClinic.data.source.local.db.dao

import `in`.co.akgroups.apnaClinic.data.source.local.db.entity.UserData
import android.arch.persistence.room.*


@Dao
interface UserDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserData(userData: UserData) : Long

    @Query("SELECT * FROM user_data WHERE id = :id")
    fun getUserData(id : Long) : UserData

    @Query("SELECT * FROM user_data")
    fun getLoggedInUser() : UserData

    @Update
    fun updateUserData(userData: UserData) : Int

    @Query("DELETE FROM user_data")
    fun deleteUserData()
}