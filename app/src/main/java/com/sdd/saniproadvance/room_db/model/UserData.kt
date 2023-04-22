package com.sdd.saniproadvance.room_db.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user_data")
data class UserData(
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    var name:String,
    var email:String,
    var password:String,
    var phoneNo:String,
    var address:String?="",
    var dob:String?="",
    var gender:String?="",
    var profileImage:String?="",
    var education:String?="",
    ): Parcelable