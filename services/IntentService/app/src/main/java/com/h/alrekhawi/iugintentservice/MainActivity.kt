package com.h.alrekhawi.iugintentservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart.setOnClickListener {
            val i = Intent(this, MyService::class.java)
            i.putExtra("msg", txtMsg.text.toString())
            startService(i)
        }
    }
}
