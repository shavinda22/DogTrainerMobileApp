package com.example.adminfordogtrainer

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddTrainer : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference

    private lateinit var editTextName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonSignUp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_trainer)

        auth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().reference.child("users")

        editTextName = findViewById(R.id.editTextName)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonSignUp = findViewById(R.id.buttonSignUp)

        buttonSignUp.setOnClickListener {
            val signupName = editTextName.text?.toString() ?: ""
            val signupEmail = editTextEmail.text?.toString() ?: ""
            val signupPassword = editTextPassword.text?.toString() ?: ""

            if (signupEmail.isNotEmpty() && signupPassword.isNotEmpty()) {
                signUpUser(signupName, signupEmail, signupPassword)
            } else {
                Toast.makeText(this@AddTrainer, "All fields are mandatory", Toast.LENGTH_SHORT).show()
            }


        }
    }

    private fun signUpUser(name: String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val userId = user?.uid
                    val role = if (email.endsWith("@trainer.com")) "trainer" else "user"

                    userId?.let {
                        val userData = User(name = name, email = email, password = password, role = role)
                        databaseReference.child(userId).setValue(userData)

                        Toast.makeText(this@AddTrainer, "SignUp Success", Toast.LENGTH_SHORT).show()
                        //finish()
                    }
                } else {
                    Toast.makeText(this@AddTrainer, "Authentication failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
