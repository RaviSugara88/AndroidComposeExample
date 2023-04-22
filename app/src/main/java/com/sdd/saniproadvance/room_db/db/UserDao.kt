package com.sdd.saniproadvance.room_db.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sdd.saniproadvance.room_db.model.UserData

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(userData: UserData)

    @Query("SELECT * FROM user_data WHERE email = :email")
    suspend fun loginUser(email:String):UserData?

    @Query("SELECT * FROM user_data WHERE id = :id")
    suspend fun getUserDetail(id: Int): UserData?

    @Query("DELETE FROM user_data WHERE id= :id")
    suspend fun deleteUser(id: Int)




}