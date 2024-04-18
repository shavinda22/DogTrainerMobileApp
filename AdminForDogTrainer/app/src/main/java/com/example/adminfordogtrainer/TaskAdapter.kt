package com.example.adminfordogtrainer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference

class TaskAdapter(
    private val taskList: List<Task>,
    private val databaseReference: DatabaseReference
) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        val textViewName: TextView = itemView.findViewById(R.id.textViewName)

        val textViewModel: TextView = itemView.findViewById(R.id.textViewModel)
        // Rest of your TaskAdapter implementation...
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_task, parent, false)
        return TaskAdapter.ViewHolder(view)
    }
    override fun onBindViewHolder(holder: TaskAdapter.ViewHolder, position: Int) {
        val taskRequest = taskList[position]
        holder.textViewTitle.text = taskRequest.taskTitle
        holder.textViewName.text =taskRequest.taskName

        holder.textViewModel.text =taskRequest.taskModel



    }
    override fun getItemCount(): Int {
        return taskList.size
    }// Your TaskViewHolder class and other methods...



}
