package com.example.adminfordogtrainer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference

class UserAdapter (
    private val userList: List<User>,
    private val databaseReference: DatabaseReference
) : RecyclerView.Adapter<UserAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewName: TextView = itemView.findViewById(R.id.textViewNamee)
        val textViewEmail: TextView = itemView.findViewById(R.id.textViewEmail)


        //val textViewNumber: TextView = itemView.findViewById(R.id.textViewNumber)
        // Rest of your TaskAdapter implementation...
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return UserAdapter.ViewHolder(view)
    }
    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) {
        val users = userList[position]
        holder.textViewName.text = users.name
        holder.textViewEmail.text =users.email

      //  holder.textViewNumber.text =users.number



    }
    override fun getItemCount(): Int {
        return userList.size
    }// Your TaskViewHolder class and other methods...






}