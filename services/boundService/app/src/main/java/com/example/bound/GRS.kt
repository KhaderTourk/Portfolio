package com.example.bound

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder

class GRS : Service() {
    var binderService :IBinder = GBS()
    lateinit var handlerThread:HandlerThread
    lateinit var handler:Handler
    lateinit var myNotificationManager: MyNotificationManager

    inner class  GBS:Binder(){
        var binder:GRS = this@GRS
    }

    override fun onBind(intent: Intent): IBinder {
        return binderService
    }

    override fun onCreate() {
        super.onCreate()
        handlerThread = HandlerThread("GENERATE RANDOM")
        handlerThread.start()
        handler = Handler(handlerThread.looper)
        handler.post{
            randomNumber()

        }
        startForeground(myNotificationManager.getNotificationId(),
            myNotificationManager.getNotification(this, Intent(this , Notification_Show::class.java)))
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        isGenerate = false
        handler.removeCallbacksAndMessages(null)
        handler.looper.quit()
        handlerThread.quit()
    }

    var isGenerate = false
    var number:Int = -1
    fun randomNumber(){
        isGenerate = true
        while (isGenerate){
            number = (Math.random()*100).toInt()
        }

    }
}
