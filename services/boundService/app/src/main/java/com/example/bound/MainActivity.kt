package com.example.bound

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var serviceConnection: ServiceConnection
    var grs:GRS? = null
    var bounded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        serviceConnection = object :ServiceConnection{
            override fun onServiceDisconnected(name: ComponentName?) {
                grs = null
            }

            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                var gbs:GRS.GBS = service as GRS.GBS
                grs = gbs.binder
            }

        }
        btnStartService.setOnClickListener {

            if(!bounded){

            var intent = Intent(this,GRS::class.java)
            bindService(intent , serviceConnection , Context.BIND_AUTO_CREATE)
            bounded = true
        }else{
                Toast.makeText(this,"Service is bounded",Toast.LENGTH_SHORT).show()
            }
    }
        btnStopService.setOnClickListener {
            if(bounded){
                unbindService(serviceConnection)
                bounded = false
            }else{
                Toast.makeText(this,"Service is unbounded",Toast.LENGTH_SHORT).show()
            }
        }
        btnGetNumber.setOnClickListener {
            if (bounded){
                tvShow.text = grs!!.number.toString()
            }else{
                Toast.makeText(this,"Service is bounded",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
