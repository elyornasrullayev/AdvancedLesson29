package com.ensoft.lesson29.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ensoft.lesson29.dao.UserDao
import com.ensoft.lesson29.model.User


@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDB : RoomDatabase(){
    abstract fun getUserDao() : UserDao

    companion object{
        @Volatile
        private var INSTANCE: UserDB? = null

        fun getDatabase(context: Context) : UserDB {
            synchronized(this){
                var instance = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDB::class.java,
                        "user_db")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}