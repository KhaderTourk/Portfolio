package com.h.alrekhawi.iugforegroundservice

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.h.alrekhawi.iugforegroundservice.MyApp.Companion.CHANNEL_ID

class MyService : Service() {


    override fun onCreate() {
        super.onCreate()
        Log.e("hzm", "onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("hzm", "onStartCommand")
        val msg = intent!!.getStringExtra("msg")

        val i = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, i, 0)

        val notificationCompat = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(getString(R.string.app_name))
            .setContentText("$msg , Service is Running")
            .setSmallIcon(R.drawable.ic_android)
            .setContentIntent(pendingIntent)
            .build()

          startForeground(1,notificationCompat)

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("hzm", "onDestroy")
    }


    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}
