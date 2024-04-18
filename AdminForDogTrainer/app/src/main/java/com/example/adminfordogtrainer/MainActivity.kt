package com.example.adminfordogtrainer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.FirebaseApp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContentView(R.layout.activity_main)

        val button3 = findViewById<Button>(R.id.button3)
        val button2 = findViewById<Button>(R.id.button2)
        val button4 = findViewById<Button>(R.id.button4)
        val button = findViewById<Button>(R.id.button)

        button3.setOnClickListener {
            val intent = Intent(this@MainActivity, AllRequests::class.java)
            startActivity(intent)
            finish()
        }

        button2.setOnClickListener {
            val intent = Intent(this@MainActivity, AllTrainers::class.java)
            startActivity(intent)
            finish()
        }

        button4.setOnClickListener {
            val intent = Intent(this@MainActivity, AllTask::class.java)
            startActivity(intent)
            finish()
        }
        button.setOnClickListener {
            val intent = Intent(this@MainActivity, AllUsers::class.java)
            startActivity(intent)
            finish()
        }
    }
}