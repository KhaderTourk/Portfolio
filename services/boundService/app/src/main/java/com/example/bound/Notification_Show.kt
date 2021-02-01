package com.example.bound

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import kotlinx.android.synthetic.main.activity_notification__show.*

class Notification_Show : AppCompatActivity() {


    lateinit var myNotificationManager: MyNotificationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification__show)

        if (intent.hasExtra("message")){
            tvnot.text = intent.getStringExtra("message")
        }

        myNotificationManager = MyNotificationManager(this)
        val intent = Intent(this , Notification_Show::class.java)

        intent.putExtra("message","fdfddfdfd")
        btnNotify.setOnClickListener {
            for (i in 0..5) {
                SystemClock.sleep(1000)
            }
            myNotificationManager.showNotification(1, "lab3", "lab notification", intent)
        }
    }
}
