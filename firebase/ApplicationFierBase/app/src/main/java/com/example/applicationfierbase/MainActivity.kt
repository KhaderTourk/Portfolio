package com.example.applicationfierbase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
lateinit var db:FirebaseFirestore
    var product = ArrayList<Product>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Access a Cloud Firestore instance from your Activity
      //  getAllProducts()
         db = Firebase.firestore
        val users = hashMapOf("Name" to "55o","po" to "6op")

        db.collection("Resturants")
            .add(users)
            .addOnSuccessListener {
                Toast.makeText(this,"success",Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener { exception ->
                Log.e("this",exception.message!!)
            }
    }
//    private fun getAllProducts() {
//
//        db.collection("Products")
//                .get()
//                .addOnSuccessListener { querySnapshot ->
//                    for (document in querySnapshot){
//                        val name:String = document.get("name") as String
//                        val price:Double = document.get("price") as Double
//                        val quantity:Int = document.get("quantity") as Int
//                        val category:String = document.get("category") as String
//                        val id = document.id
//                        val pro = Product(id,name ,price ,quantity ,category)
//                        product.add(pro)
//                    }
//                }.addOnFailureListener{exception ->
//                    Log.e("abd",exception.message!!)
//                }
//    }
    inner class Product(val  id:String ,val name:String, val price:Double, val quantity:Int, val category:String) {
    }
    }
