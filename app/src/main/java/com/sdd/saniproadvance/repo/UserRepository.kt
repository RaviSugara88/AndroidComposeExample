package com.sdd.saniproadvance.repo

import com.sdd.saniproadvance.room_db.db.UserDao
import com.sdd.saniproadvance.room_db.model.UserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.util.concurrent.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {

    suspend fun insertUser(userData: UserData) = withContext(Dispatchers.IO){
        userDao.addUser(userData)
    }

    suspend fun loginUser(email: String):UserData? = userDao.loginUser(email)


    suspend fun getUserDetail(id: Int):UserData? = userDao.getUserDetail(id)

    suspend fun deleteUser(id:Int) = withContext(Dispatchers.IO) { userDao.deleteUser(id) }

}