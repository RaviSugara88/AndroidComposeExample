package com.sdd.saniproadvance.retrofit.util

import com.sdd.saniproadvance.room_db.model.UserData

sealed class UserState{
    class Success(val data: UserData)

    object Empty : UserState()
}
