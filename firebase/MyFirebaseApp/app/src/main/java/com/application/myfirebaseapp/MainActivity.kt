package com.application.myfirebaseapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore

import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    var db: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = Firebase.auth
        db = Firebase.firestore
        btn_signup.setOnClickListener {
            createNewAccount(ed_email.text.toString(), ed_pass.text.toString())
        }

        tv_login.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }

    private fun createNewAccount(email: String, pass: String) {
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "تم التسجيل بنجاح", Toast.LENGTH_SHORT).show()
                val user = auth.currentUser
                Log.e("TestAuthn", "user ${user!!.uid} + ${user.email}")
                addUser(
                    user.uid,
                    ed_name.text.toString(),
                    user.email!!,
                    ed_phone.text.toString(),
                    ed_address.text.toString()
                )

            } else {
                Toast.makeText(this, "فشل في التسجيل", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun addUser(id: String, name: String, email: String, phone: String, address: String) {
        var user =
            hashMapOf(
                "id" to id,
                "name" to name,
                "email" to email,
                "phone" to phone,
                "address" to address
            )

        db!!.collection("users").add(user).addOnSuccessListener { documentReference ->
            Log.e("Document ID", documentReference.id)

        }.addOnFailureListener { exception ->

        }

    }

}
