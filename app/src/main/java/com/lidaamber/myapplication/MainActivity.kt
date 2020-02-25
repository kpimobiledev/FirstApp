package com.lidaamber.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        greetButton.setOnClickListener {
            val name = nameEditText.text.toString()

            val intent = Intent(this, NotificationService::class.java)
            intent.putExtra(NotificationService.NOTIFICATION_NAME, name)

            startService(intent)
        }
    }

}

