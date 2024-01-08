package com.ensoft.lesson29.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
data class User(val userName: String, val age: Int){
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null

}
