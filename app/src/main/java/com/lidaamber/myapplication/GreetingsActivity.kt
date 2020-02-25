package com.lidaamber.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_greetings.*

class GreetingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_greetings)

        intent?.let {
            val result = it.getStringExtra(EXTRA_NAME)

            val greetings = String.format(getString(R.string.greetings, result))
            greetingsTextView.text = greetings
        }
    }

    companion object {
        const val EXTRA_NAME = "com.lidaamber.myapplication.name"
    }
}

