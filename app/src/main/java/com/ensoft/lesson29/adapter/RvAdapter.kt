package com.ensoft.lesson29.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ensoft.lesson29.R
import com.ensoft.lesson29.model.User


class RvAdapter(private var userList: ArrayList<User>)
    : RecyclerView.Adapter<RvAdapter.UserViewHolder>() {
    inner class UserViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val tvTitle = itemview.findViewById<TextView>(R.id.tv_title)
        val tvAge = itemview.findViewById<TextView>(R.id.tv_age)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
        return UserViewHolder(view)
    }


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.tvTitle.text = userList[position].userName
        holder.tvAge.text = userList[position].age.toString()
    }

    override fun getItemCount(): Int {
        return userList.size
    }
    fun setData(userList: ArrayList<User>){
        this.userList = userList
        notifyDataSetChanged()
    }
}