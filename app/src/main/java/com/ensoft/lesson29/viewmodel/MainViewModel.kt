package com.ensoft.lesson29.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ensoft.lesson29.model.User
import com.ensoft.lesson29.repository.UserRepository


class MainViewModel: ViewModel() {
    fun insert(context: Context, user: User){
        UserRepository.insert(context, user)
    }
    fun getAllData(context: Context) : LiveData<List<User>> {
        return UserRepository.getAllUser(context)
    }
}