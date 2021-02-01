package com.h.alrekhawi.iugfusedlocation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng

class MainActivity : AppCompatActivity() {

    val TAG = "hzm"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGetLocation.setOnClickListener {
            getLastLocation()
        }
    }

    private fun getLastLocation(){
        val locationClient  = LocationServices.getFusedLocationProviderClient(this)
        locationClient.lastLocation
            .addOnSuccessListener {location ->
                if(location!=null){
                    val latitude = location.latitude
                    val longitude = location.longitude
                    val provider = location.provider

                    val latlang = LatLng(latitude,longitude)

                    Log.e(TAG,latitude.toString())
                    Log.e(TAG,longitude.toString())
                    Log.e(TAG,provider)
                }
            }
            .addOnFailureListener { exception ->
                Log.e(TAG,exception.message)
            }
    }

}
