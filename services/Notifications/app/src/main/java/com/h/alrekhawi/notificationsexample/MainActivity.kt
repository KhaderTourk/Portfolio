package com.h.alrekhawi.notificationsexample

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnNotify.setOnClickListener {
            createNotificationChannel()
        }
    }

    private fun createNotificationChannel() {
        val CHANNEL_ID = "ServiceChannelExample"
        var manager: NotificationManager?=null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(CHANNEL_ID, "Example Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT)
            manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        } else {
            manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        }
            val notificationIntent = Intent(this, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

            val notification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Service Example")
                .setContentText("Eng. Hazem Al Rekhawi")
                .setSmallIcon(R.drawable.ic_stat_new_message)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.largelogo))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build()

            manager!!.notify(1,notification)
    }
}
