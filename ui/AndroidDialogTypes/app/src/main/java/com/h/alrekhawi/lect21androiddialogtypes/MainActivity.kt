package com.h.alrekhawi.lect21androiddialogtypes

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_dialog.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnCustom.setOnClickListener {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.custom_dialog)
            dialog.btn.setOnClickListener {
                Toast.makeText(this, "AAA", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            dialog.show()
        }


        txtTime.setOnClickListener {
            val currentTime = Calendar.getInstance()
            val hour = currentTime.get(Calendar.HOUR_OF_DAY)
            val minute = currentTime.get(Calendar.MINUTE)

            val picker = TimePickerDialog(this,
                    TimePickerDialog.OnTimeSetListener { timePicker, h, m ->
                        txtTime.setText("$h : $m")

                    }, hour, minute, true)
            picker.show()
        }



        txtDate.setOnClickListener {
            val currentDate = Calendar.getInstance()
            val day = currentDate.get(Calendar.DAY_OF_MONTH)
            val month = currentDate.get(Calendar.MONTH)
            val year = currentDate.get(Calendar.YEAR)
            val picker = DatePickerDialog(this,
                    DatePickerDialog.OnDateSetListener { datePicker, y, m, d ->
                        txtDate.setText("$y / ${m + 1} / $d")
                    }, year, month, day)

            picker.show()
        }






        btnAlert.setOnClickListener {

            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Delete Post")
            alertDialog.setMessage("Are you sure to delete post?")
            alertDialog.setIcon(R.drawable.ic_delete)
            alertDialog.setCancelable(false)

            alertDialog.setPositiveButton("Yes") { d, i ->
                Toast.makeText(this, "Yes", Toast.LENGTH_SHORT).show()
            }

            alertDialog.setNegativeButton("No") { d, i ->
                Toast.makeText(this, "No", Toast.LENGTH_SHORT).show()
                //d.cancel()
            }

            alertDialog.setNeutralButton("Rate Me") { d, i ->
                Toast.makeText(this, "Rate Me", Toast.LENGTH_SHORT).show()
            }

            alertDialog.create().show()
        }

    }
}