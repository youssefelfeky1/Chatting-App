package com.example.chatapplication.ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapplication.R
import com.example.chatapplication.ui.Model.Message
import com.google.firebase.auth.FirebaseAuth

class MessageAdapter(val context: Context,val messageList:ArrayList<Message>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val ITEM_RECEIVE = 1
    val ITEM_SENT = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == 1){
            val view :View = LayoutInflater.from(context).inflate(R.layout.receive,parent,false)
            return receiveViewHolder(view)
        }
        else{
            val view :View = LayoutInflater.from(context).inflate(R.layout.sent,parent,false)
            return sentViewHolder(view)

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder::class.java == sentViewHolder::class.java)
        {
            val viewHolder = holder as sentViewHolder
            holder.sentMessage.text = messageList[position].message

        }
        else{
            val viewHolder = holder as receiveViewHolder
            holder.receivedMessage.text = messageList[position].message

        }
    }

    override fun getItemViewType(position: Int): Int {
        if (FirebaseAuth.getInstance().currentUser?.uid.equals(messageList[position].senderId))
        {
            return ITEM_SENT
        }
        else{
            return ITEM_RECEIVE

        }

    }

    override fun getItemCount(): Int {
        return messageList.size
    }
    class sentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val sentMessage = itemView.findViewById<TextView>(R.id.sentTxt)

    }
    class receiveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val receivedMessage = itemView.findViewById<TextView>(R.id.receiveTxt)

    }
}