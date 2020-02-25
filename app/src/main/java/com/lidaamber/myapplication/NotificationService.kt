package com.lidaamber.myapplication

import android.app.IntentService
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.util.concurrent.atomic.AtomicInteger

class NotificationService : IntentService("Notifications") {

    override fun onHandleIntent(intent: Intent?) {
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O && !notificationManager.has(CHANNEL_ID)) {
            createNotificationChannel()
        }

        displayNotification("Starting service...")

        Thread.sleep(5000L)

        intent?.let {
            val name = it.getStringExtra(NOTIFICATION_NAME)
            displayNotification("Hello $name!")
        }

        stopSelf()

    }

    private fun displayNotification(text: String, title: String = "Service sample") {
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_android)
            .setContentTitle(title)
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            notify(getNextID(), builder.build())
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val name = getString(R.string.channel_name)
        val descriptionText = getString(R.string.channel_description)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }

        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

    }

    companion object {
        const val NOTIFICATION_NAME = "com.lidaamber.myapplication.name"
        private const val CHANNEL_ID = "My notification id"

        private val internalID = AtomicInteger(0)
        private fun getNextID() = internalID.incrementAndGet()
    }

}

@RequiresApi(Build.VERSION_CODES.O)
fun NotificationManager.has(channelID: String) =
    this.getNotificationChannel(channelID) != null