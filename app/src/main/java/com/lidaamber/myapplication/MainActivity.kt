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
            val intent = Intent(this@MainActivity, GreetingsActivity::class.java)

            val name = nameEditText.text.toString()
            intent.putExtra(GreetingsActivity.EXTRA_NAME, name)

            startActivity(intent)
        }
    }
}

