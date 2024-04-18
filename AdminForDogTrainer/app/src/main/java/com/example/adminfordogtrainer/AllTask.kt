package com.example.adminfordogtrainer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AllTask : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var taskList: MutableList<Task>
    private val databaseReference: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("Task")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_trask)

        val button = findViewById<Button>(R.id.button6)

        button.setOnClickListener {
            val intent = Intent(this@AllTask, AddTask::class.java)
            startActivity(intent)
            finish()
        }
        val backButton = findViewById<Button>(R.id.button7)
        backButton.setOnClickListener {

            val intent = Intent(this@AllTask, MainActivity::class.java)
            startActivity(intent)


        }

        ///////////////////////////////////////////////
        recyclerView = findViewById(R.id.recyclerView2)
        recyclerView.layoutManager = LinearLayoutManager(this)

        taskList = mutableListOf()
        taskAdapter = TaskAdapter(taskList, databaseReference)
        recyclerView.adapter = taskAdapter
        val tasksReference =FirebaseDatabase.getInstance().getReference("tasks")
        tasksReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.d("Firebase Database", "onDataChange triggered")

                taskList.clear()

                for (childSnapshot in dataSnapshot.children) {
                    val completedTask = childSnapshot.getValue(Task::class.java)
                    completedTask?.let {
                        taskList.add(it)
                        Log.d("Firebase Database", "Task: $it")
                    }
                }

                taskAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e(
                    "Firebase Database",
                    "Error getting support requests",
                    databaseError.toException()
                )
            }
        })
    }
    override fun onBackPressed() {
        try {
            Log.d("AllTask", "onBackPressed called")
            // Add your custom back navigation logic here
            // Log additional information as needed
            super.onBackPressed()
        } catch (e: Exception) {
            Log.e("AllTask", "Exception in onBackPressed", e)
        }
    }
}
