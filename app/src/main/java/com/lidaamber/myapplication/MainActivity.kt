package com.lidaamber.myapplication

import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var bluetoothReceiver: BroadcastReceiver
    private lateinit var greetingReceiver: BroadcastReceiver


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        greetButton.setOnClickListener {
            val intent = Intent(GREET_ACTION)
            intent.putExtra(EXTRA_NAME, nameEditText.text.toString())

            LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        val filter = IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED)
        bluetoothReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                Log.d(LOG_TAG, "Bluetooth state changed")
            }

        }
        registerReceiver(bluetoothReceiver, filter)

        val localFilter = IntentFilter(GREET_ACTION)
        greetingReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val name = intent?.getStringExtra(EXTRA_NAME)

                Log.d(LOG_TAG, name)
            }

        }
        LocalBroadcastManager.getInstance(this).registerReceiver(greetingReceiver, localFilter)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(bluetoothReceiver)
        LocalBroadcastManager.getInstance(this).unregisterReceiver(greetingReceiver)
    }

    companion object {
        const val LOG_TAG = "Broadcasts"
        const val EXTRA_NAME = "com.lidaamber.myapplication.EXTRA_NAME"
        const val GREET_ACTION = "com.lidaamber.myapplication.GREET"
    }

}
