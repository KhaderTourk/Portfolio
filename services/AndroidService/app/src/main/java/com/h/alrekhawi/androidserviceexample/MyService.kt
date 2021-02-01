package com.h.alrekhawi.androidserviceexample

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.CountDownTimer
import android.os.IBinder
import android.util.Log
import android.widget.Toast


class MyService : Service() {

    lateinit var mp: MediaPlayer

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onCreate() {
        super.onCreate()
        mp = MediaPlayer.create(this, R.raw.ekhlas)
        mp.isLooping = true
        Toast.makeText(this, "Service Created", Toast.LENGTH_SHORT).show()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show()
        mp.start()
        val s = intent!!.getStringExtra("msg")
        Toast.makeText(applicationContext, "s = $s", Toast.LENGTH_SHORT).show()

        object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                Log.e("hzm","seconds remaining: " + millisUntilFinished / 1000)
            }

            override fun onFinish() {
                Toast.makeText(applicationContext,"Self Stopping",Toast.LENGTH_SHORT).show()
                stopSelf()
            }
        }.start()

        //return super.onStartCommand(intent, flags, startId)
        //return START_NOT_STICKY //Service not restarted
        //return START_STICKY //Service Restarted with Null Intent Data
        return START_REDELIVER_INTENT //Service Restarted with Intent Data
    }

    override fun onDestroy() {
        super.onDestroy()
        mp.pause()
        //mp.stop()
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_SHORT).show()
    }
}
