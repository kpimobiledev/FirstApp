package com.lidaamber.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pressButton.setOnClickListener {
            Toast.makeText(this, R.string.button_pressed, Toast.LENGTH_LONG).show()
        }
    }
}

