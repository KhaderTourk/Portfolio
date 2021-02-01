package com.example.dbserviseaplication

import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = DBHelper(this)


        btn_Login.setOnClickListener {
            val persons = db.getAllPerson()
            for (i in persons) {
                val myAsyncTask = MyAsyncTask()
                val name = i.name
                val password = i.password
                myAsyncTask.execute(et_userName.text.toString(), et_password.text.toString(),name,password)
            }
        }
        btnSignup.setOnClickListener {
            val intent = Intent(this, Signup::class.java)
            startActivity(intent)
        }
    }
    inner class MyAsyncTask : AsyncTask<String?, String?, String?>(){

        override fun onPreExecute() {
            super.onPreExecute()
            progressDialog = ProgressDialog(this@MainActivity)
            progressDialog.setMessage("جاري التحقق من الحساب...")
            progressDialog.setCancelable(false)
            progressDialog.show()
        }

        override fun doInBackground(vararg params: String?): String? {

            if (params[0] != "" && params[1] != ""){

                if (params[0] == params[2] && params[1] == params[3]) {
                    Thread.sleep(15000)
                    return "yes"
                }else
                    return "no"
            } else
                return "miss"
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            if (result == "yes"){
                val intent1 = Intent(this@MainActivity, HomeActivity::class.java)
                startActivity(intent1)

            }else if (result == "no") {
                Toast.makeText(this@MainActivity, "User undefine", Toast.LENGTH_SHORT).show()
            }else if (result == "miss") {
                Toast.makeText(this@MainActivity, "please fill the fields", Toast.LENGTH_SHORT)
                    .show()
            }
            progressDialog.dismiss()
        }

    }
}
