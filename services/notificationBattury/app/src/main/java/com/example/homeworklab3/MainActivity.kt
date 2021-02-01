package com.example.homeworklab3

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.battery_alert_dialog.view.*

class MainActivity : AppCompatActivity(),BatteryStatus.BatteryChangedListner {
    private lateinit var alertDialog: AlertDialog
    override fun onBatteryChanged(isChanged: Boolean) {
      if (!isChanged){
          alertDialog.dismiss()
      }else{
          if (!alertDialog.isShowing) {
              alertDialog.show()
          }
      }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showBatteryAlert()
        registerReceiver(BatteryStatus(), IntentFilter(Intent.ACTION_BATTERY_CHANGED))
    }

    override fun onResume() {
        super.onResume()
        BatteryStatus.batteryChangedListner= this
    }
    fun showBatteryAlert(){
        val alert = AlertDialog.Builder(this)
        val view = LayoutInflater.from(this).inflate(R.layout.battery_alert_dialog,null)
        alert.setView(view)
        view.btn_ok.setOnClickListener {
            alertDialog.dismiss()
        }
        alertDialog = alert.create()
    }
}
