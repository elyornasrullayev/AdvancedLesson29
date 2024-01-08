package com.ensoft.lesson29.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.ensoft.lesson29.database.UserDB
import com.ensoft.lesson29.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


class UserRepository {

    companion object{
        private var userDB: UserDB? = null
        fun initDataBase(context: Context) : UserDB {
            return UserDB.getDatabase(context)
        }
        fun insert(context: Context, user: User){
            userDB = initDataBase(context)
            CoroutineScope(IO).launch {
                userDB?.getUserDao()?.insert(user)
            }
        }
        fun getAllUser(context: Context) : LiveData<List<User>> {
            userDB = initDataBase(context)
            return userDB?.getUserDao()?.getAllUser()!!
        }
    }
}