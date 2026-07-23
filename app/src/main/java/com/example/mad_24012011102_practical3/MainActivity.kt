package com.example.mad_24012011102_practical3

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.CallLog
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Explicit Intent
        val loginButton = findViewById<Button>(R.id.login_button_1)

        loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("username", "maharsh")
            intent.putExtra("password", "123")
            startActivity(intent)
        }

        implicitIntent()
    }

    private fun implicitIntent() {

        // Browse Website
        findViewById<Button>(R.id.browse_button).setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com")
            )
            startActivity(intent)
        }

        // Phone Dialer
        findViewById<Button>(R.id.call_button).setOnClickListener {

            val number = findViewById<EditText>(R.id.enter_phone_no)
                .text
                .toString()
                .trim()

            if (number.isNotEmpty()) {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:$number")
                startActivity(intent)
            }
        }

        // Call Log
        findViewById<Button>(R.id.call_log_button).setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.type = CallLog.Calls.CONTENT_TYPE
            startActivity(intent)
        }

        // Gallery
        findViewById<Button>(R.id.gallery_button).setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.type = "image/*"
            startActivity(intent)
        }

        // Camera
        findViewById<Button>(R.id.camera_button).setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(intent)
        }

        // Alarm
        findViewById<Button>(R.id.alarm_button).setOnClickListener {
            val intent = Intent(AlarmClock.ACTION_SHOW_ALARMS)
            startActivity(intent)
        }
    }
}