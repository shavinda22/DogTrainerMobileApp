package com.example.adminfordogtrainer

import android.app.DownloadManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference

class RequestAdapter(
    private val requestList: MutableList<SupportRequest>,
    private val databaseReference: DatabaseReference
): RecyclerView.Adapter<RequestAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        val textViewName: TextView = itemView.findViewById(R.id.textViewName)

        val textViewModel: TextView = itemView.findViewById(R.id.textViewModel)
        // Rest of your TaskAdapter implementation...
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_request, parent, false)
        return RequestAdapter.ViewHolder(view)
    }
    override fun onBindViewHolder(holder: RequestAdapter.ViewHolder, position: Int) {
        val supportRequest = requestList[position]
        holder.textViewTitle.text = supportRequest.reason
        holder.textViewName.text = "Date: ${supportRequest.year}/${supportRequest.month}/${supportRequest.day}"
        holder.textViewModel.text = "Time: ${supportRequest.hour}:${supportRequest.minute}"



    }
    override fun getItemCount(): Int {
        return requestList.size
    }// Your TaskViewHolder class and other methods...

}