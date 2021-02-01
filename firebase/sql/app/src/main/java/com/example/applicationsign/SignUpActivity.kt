package com.example.applicationsign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        val db = SQL(this)
        buttonSignup.setOnClickListener {
            if (et_username.text.toString() != "" && et_pass.text.toString() != "" && et_email.text.toString() != ""){
            if (db.insertPerson(et_username.text.toString(), et_pass.text.toString(),et_email.text.toString(),et_phonenumber.text.toString())) {
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
            }else
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }else
                Toast.makeText(this, "please fill the fields", Toast.LENGTH_SHORT).show()
        }

        btnLogin.setOnClickListener {
            val i= Intent(this, MainActivity::class.java)
            startActivity(i)
        }


    }
}
