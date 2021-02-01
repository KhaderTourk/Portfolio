package com.application.myfirebaseapp

import android.content.Intent
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    lateinit var myNotificationManager: MyNotificationManager
    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.e("NEW_TOKEN", p0)

    }

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        myNotificationManager = MyNotificationManager(applicationContext)
        myNotificationManager.showNotification(
            1, p0.notification!!.title!!, p0.notification!!.body!!,
            Intent(applicationContext, MainActivity::class.java)
        )

    }
}