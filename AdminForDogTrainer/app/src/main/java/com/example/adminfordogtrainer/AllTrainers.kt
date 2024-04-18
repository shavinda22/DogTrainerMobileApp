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

class AllTrainers : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter
    private lateinit var userList: MutableList<User>
    private val databaseReference: DatabaseReference =
        FirebaseDatabase.getInstance().reference.child("users")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_trainers)

        val backButton = findViewById<Button>(R.id.button9)
        backButton.setOnClickListener {

            val intent = Intent(this@AllTrainers, MainActivity::class.java)
            startActivity(intent)
            finish()

        }
        val buttonSignUp = findViewById<Button>(R.id.button5)
        buttonSignUp.setOnClickListener {

            val intent = Intent(this@AllTrainers, AddTrainer::class.java)
            startActivity(intent)
            finish()

        }
        recyclerView = findViewById(R.id.recyclerView8)
        recyclerView.layoutManager = LinearLayoutManager(this)

        userList = mutableListOf()
        userAdapter = UserAdapter(userList, databaseReference)
        recyclerView.adapter = userAdapter
        val usersReference = FirebaseDatabase.getInstance().reference.child("users")
        usersReference.orderByChild("role").equalTo("trainer").addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.d("Firebase Database", "onDataChange triggered")



                for (childSnapshot in dataSnapshot.children) {
                    try {
                        val user = childSnapshot.getValue(User::class.java)
                        if (user != null) {
                            userList.add(user)
                            Log.d("Firebase Database", "User: $user")
                        } else {
                            Log.w("Firebase Database", "User is null for child: $childSnapshot")
                        }
                    } catch (e: Exception) {
                        Log.e("Firebase Database", "Error parsing User", e)
                    }

                }
                userAdapter.notifyDataSetChanged()

                Log.d("Firebase Database", "User list size: ${userList.size}")
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
    @Deprecated("Deprecated in Java")
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