package com.example.mvvmexample.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

const val CURRENT_USER_ID = 0;
@Entity
data class User (
    var id:Int ? =null,
    var name:String ? =null,
    var email : String ? = null,
    var email_verified :String? = null,
) {
    @PrimaryKey(autoGenerate = true)
    var uId : Int = CURRENT_USER_ID
}