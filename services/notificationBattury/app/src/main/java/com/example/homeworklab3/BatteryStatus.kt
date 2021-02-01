package com.example.homeworklab3

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager

class BatteryStatus:BroadcastReceiver() {
    lateinit var myNotificationManager: MyNotificationManager
    override fun onReceive(context: Context?, intent: Intent?) {
        if (batteryChangedListner != null){
            if (checkBatteryStatus(context!!)!! <=10){
showNotification(context)
                batteryChangedListner!!.onBatteryChanged(true)
            }else{
cancleNotification(context)
                batteryChangedListner!!.onBatteryChanged(false)
            }
        }
    }
    fun showNotification(context: Context){
        myNotificationManager = MyNotificationManager(context)
        myNotificationManager.showNotification(1,"battery status","your battery is law ,please connect charger",
            Intent(context,MainActivity::class.java))
    }
    fun cancleNotification(context: Context){
        myNotificationManager = MyNotificationManager(context)
        myNotificationManager.cancleNotification(1)
    }

    fun checkBatteryStatus(context:Context):Float?{
        val ifilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        val batteryStatus: Intent? = context.registerReceiver(null,ifilter)
        val batteryPct: Float? = batteryStatus?.let { intent ->
            val level: Int = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
            val scale: Int = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
            level * 100 / scale.toFloat()
        }
        return batteryPct
    }
    interface BatteryChangedListner{
        fun onBatteryChanged(isChangedListner: Boolean)
    }
    companion object{
        var batteryChangedListner:BatteryChangedListner? = null
    }
}