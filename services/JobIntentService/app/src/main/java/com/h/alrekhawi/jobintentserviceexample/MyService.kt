package com.h.alrekhawi.jobintentserviceexample

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.os.SystemClock
import android.util.Log
import androidx.core.app.JobIntentService

class MyService : JobIntentService() {

    companion object {
        fun enqueueWork(context: Context, work: Intent) {
            enqueueWork(context, MyService::class.java, 123, work)
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.e("hzm", "onCreate")
    }

    override fun onHandleWork(intent: Intent) {
        Log.e("hzm", "onHandleWork")
        val msg = intent!!.getStringExtra("msg")
        for (i in 0..9) {
            if (isStopped) return
            SystemClock.sleep(1000)
            Log.e("hzm", "$msg = $i")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("hzm", "onDestroy")
    }

    override fun onStopCurrentWork(): Boolean {
        Log.e("hzm", "onStopCurrentWork")
        return super.onStopCurrentWork()
    }
}
