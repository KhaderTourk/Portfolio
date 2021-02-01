package com.h.alrekhawi.iugvolleyobjectarrayrequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val URL_OBJ = "https://api.androidhive.info/volley/person_object.json"
    val URL_ARR = "https://api.androidhive.info/volley/person_array.json"
    val URL_COR = "https://corona.ps/API/cases"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnObject.setOnClickListener {
            getStringRequest()
        }

        btnArray.setOnClickListener {
            getJSONArray()
        }
    }

    fun getJSONObject() {

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, URL_COR, null,
            Response.Listener { response ->
                Log.e("hzm", response.toString())

                 val name = response.getString("name")
                 val email = response.getString("email")

                 val phone = response.getJSONObject("phone")
                 val home = phone.getString("home")
                 val mobile = phone.getString("mobile")


            },
            Response.ErrorListener { error ->
                Log.e("hzm Error", error.message)
            })

        MySingleton.getInstance()!!.addRequestQueue(jsonObjectRequest)
        MySingleton.getInstance()!!.addRequestQueue(jsonObjectRequest, "jsonObject")
    }

    fun getJSONArray() {

        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, URL_ARR, null,
            Response.Listener { response ->
                Log.e("hzm", response.toString())

                var data = ArrayList<HashMap<String, String>>()

            },
            Response.ErrorListener { error ->
                Log.e("hzm Error", error.message)
            })

        MySingleton.getInstance()!!.addRequestQueue(jsonArrayRequest)

    }

    fun getStringRequest() {
        val stringRequest = StringRequest(Request.Method.GET, URL_COR,
            Response.Listener { response ->
                Log.e("hzm", response)
            }, Response.ErrorListener { error ->
                Log.e("hzm Error", error.message)
            })

        MySingleton.getInstance()!!.addRequestQueue(stringRequest)
    }
}
