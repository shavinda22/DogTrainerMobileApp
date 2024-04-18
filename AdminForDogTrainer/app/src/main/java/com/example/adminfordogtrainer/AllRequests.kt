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

class AllRequests : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var requestAdapter: RequestAdapter
    private lateinit var requestList: MutableList<SupportRequest>
    private val databaseReference: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("supportRequests")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_requests)




        val backButton = findViewById<Button>(R.id.button8)
        backButton.setOnClickListener {

            val intent = Intent(this@AllRequests, MainActivity::class.java)
            startActivity(intent)


        }
        requestList = mutableListOf()
        ///////////////////////////////////////////////
        recyclerView = findViewById(R.id.recyclerView3)
        recyclerView.layoutManager = LinearLayoutManager(this)


        requestAdapter = RequestAdapter(requestList, databaseReference)


        recyclerView.adapter = requestAdapter

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.d("Firebase Database", "onDataChange triggered")

                requestList.clear()


                for (userSnapshot in dataSnapshot.children) {
                    for (childSnapshot in userSnapshot.children) {
                        val request = childSnapshot.getValue(SupportRequest::class.java)
                        if (request?.status == "notaccept") {
                            requestList.add(request)
                        }
                    }
                }


                requestAdapter.notifyDataSetChanged()
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
            Log.d("AllRequest", "onBackPressed called")
            // Add your custom back navigation logic here
            // Log additional information as needed
            super.onBackPressed()
        } catch (e: Exception) {
            Log.e("AllTRequest", "Exception in onBackPressed", e)
        }
    }
}