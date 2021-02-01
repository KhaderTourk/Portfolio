package com.h.alrekhawi.lect27androidruntimepermissions

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var imageURI:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgPick.setOnClickListener {
            //val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            //startActivityForResult(gallery, 100)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(arrayOf(Manifest.permission.CAMERA), 2000)
                    return@setOnClickListener
                } else {
                    val camera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(camera, 200)
                }

            } else {
                val camera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(camera, 200)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 100) {
            Log.e("hzm", data!!.data.toString())
            imgPick.setImageURI(data!!.data)
            imageURI=data.data.toString()
        } else if (resultCode == Activity.RESULT_OK && requestCode == 200) {
            val anyB = data!!.extras!!.get("data")
            imgPick.setImageBitmap(anyB as Bitmap)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            2000 -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    val camera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(camera, 200)
                } else {
                    finish()
                }
            }
        }
    }
}