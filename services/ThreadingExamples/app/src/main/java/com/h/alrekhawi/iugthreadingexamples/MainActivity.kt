package com.h.alrekhawi.iugthreadingexamples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e("hzm",Thread.currentThread().name)
        Log.e("hzm",Thread.currentThread().id.toString())


        Thread{
            for(i in 0..5){
                Log.e("hzm","i = $i")
                Thread.sleep(1000)
                Log.e("hzm",Thread.currentThread().name)
            }

//            runOnUiThread {
//                tvMsg.text="Loop is Finished"
//            }

//            tvMsg.post {
//                tvMsg.text="Loop is Finished"
//            }

            tvMsg.postDelayed({tvMsg.text="Loop is Finished"},3000)


        }.start()



        //Log.e("hzm","Eng. Hazem Al Rekhawi")

        /*btnStart.setOnClickListener {

            val t1 = Thread(Runnable {
                while (true){
                    Log.e("hzm",Thread.currentThread().name)
                    Log.e("hzm",Thread.currentThread().id.toString())
                }
            })

            t1.start()
        }*/
    }
}
