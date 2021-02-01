package com.h.alrekhawi.iugintentservice

import android.app.IntentService
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.SystemClock
import android.util.Log

class MyService : IntentService("AAA") {

    override fun onCreate() {
        super.onCreate()
        Log.e("hzm", "onCreate")
    }

    override fun onHandleIntent(intent: Intent?) {
        Log.e("hzm", "onHandleIntent")

        val msg = intent!!.getStringExtra("msg")

        for( i in 0..9)
        {
          Log.e("hzm","$msg : $i")
            SystemClock.sleep(1000)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("hzm", "onDestroy")
    }
}
