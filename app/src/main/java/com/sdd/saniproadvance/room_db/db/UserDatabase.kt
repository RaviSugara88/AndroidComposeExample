package com.sdd.saniproadvance.room_db.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sdd.saniproadvance.room_db.model.UserData


@Database(
    entities = [UserData::class], version = 1, exportSchema = false
)
abstract class UserDatabase:RoomDatabase() {

    abstract fun userDao() : UserDao

}