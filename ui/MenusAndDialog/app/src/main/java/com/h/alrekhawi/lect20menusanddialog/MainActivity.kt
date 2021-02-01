package com.h.alrekhawi.lect20menusanddialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.PopupMenu
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnPopup.setOnClickListener {
            val popup = PopupMenu(this, btnPopup)
            popup.menuInflater.inflate(R.menu.mainmenu, popup.menu)
            popup.setOnMenuItemClickListener { x ->
                when (x.itemId) {
                    R.id.shareitem -> Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show()
                    R.id.saveitem -> Toast.makeText(this, "Save", Toast.LENGTH_SHORT).show()
                }
                true
            }
            popup.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mainmenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.shareitem -> Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show()
            R.id.saveitem -> Toast.makeText(this, "Save", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}