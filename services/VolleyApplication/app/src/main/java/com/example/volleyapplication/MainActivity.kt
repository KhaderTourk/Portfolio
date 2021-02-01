package com.example.volleyapplication

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), CaseAdapter.onClick {

var url = "https://corona.ps/API/cases"
    lateinit var progressDialog: ProgressDialog
    lateinit var caseAdapter: CaseAdapter
    lateinit var casesList: ArrayList<Case>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("loading....")
        progressDialog.setCancelable(false)
        casesList = ArrayList()
        progressDialog.show()

        getCases()

    }

    fun getCases() {
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                Log.e("hzm",response.toString())
                progressDialog.dismiss()
                val data = response.getJSONObject("data")
                val casesArray = data.getJSONArray("cases")
                for (i in 0 until casesArray.length()){
                    casesList.add(Case(casesArray.getJSONObject(i).getString("case_number"),
                        casesArray.getJSONObject(i).getString("case_age")
                        ,casesArray.getJSONObject(i).getString("case_gender")
                    ,casesArray.getJSONObject(i).getString("case_location")
                    ,casesArray.getJSONObject(i).getString("case_diagnose_date")
                    ,casesArray.getJSONObject(i).getString("case_source_of_infection")
                    ,casesArray.getJSONObject(i).getString("case_condition")
                        ,casesArray.getJSONObject(i).getString("case_quarantine")
                        ,casesArray.getJSONObject(i).getString("case_community")))
                }
                caseAdapter = CaseAdapter(this, casesList, this)
                recycle.adapter = caseAdapter
                recycle.layoutManager = LinearLayoutManager(this)
            },
            Response.ErrorListener { error -> })
        VolleySingleton.getInstance()!!.addToRequestQueue(jsonObjectRequest)
    }
    override fun onClickItem(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}