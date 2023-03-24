package com.example.chatapplication.ui.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapplication.R
import com.example.chatapplication.ui.Model.User
import com.example.chatapplication.ui.ui.ChatActivity

class UserAdapter(var context:Context,var userList:ArrayList<User>): RecyclerView.Adapter<UserAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view :View = LayoutInflater.from(context).inflate(R.layout.user_layout,parent,false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.userNameTxt.text = userList[position].name

        holder.itemView.setOnClickListener{
            val intent = Intent(context, ChatActivity::class.java)
            intent.putExtra("name",userList[position].name)
            intent.putExtra("uid",userList[position].uid)
            context.startActivity(intent)

        }

    }

    override fun getItemCount(): Int {
        return userList.size
    }
    class ViewHolder(itemView: View) :androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView)
    {
        val userNameTxt = itemView.findViewById<TextView>(R.id.userNameTxt)

    }

}