package com.h.alrekhawi.iugfirestoredatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var db: FirebaseFirestore
    val TAG = "hzm"
    lateinit var product :ArrayList<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        product = ArrayList<Product>()
        db = Firebase.firestore

        btnAddUser.setOnClickListener {
            addUserToDB(
                txtUsername.text.toString(),
                txtEmail.text.toString(),
                txtLevel.text.toString().toInt()
            )
        }

        btnGetAllUsers.setOnClickListener {
            getAllUsers()
        }

        btnGetAllUsersLimitOrder.setOnClickListener {
            getAllUsersByLimit()
        }

        btnGetAllUsersWhere.setOnClickListener {
            getAllUsersByWhere()
        }

        btnDeleteUser.setOnClickListener {
            deleteUserById("QQX7nlICRAHAsftu3B4u")
        }

        btnDeleteUserField.setOnClickListener {
            deleteUserEmailField("RU71OkMt6rr4EmyPltuo")
        }
    }

    private fun addUserToDB(name: String, email: String, level: Int) {

        val user = hashMapOf("name" to name, "email" to email, "level" to level)

        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.e(TAG, "User added Successfully with user id ${documentReference.id}")
            }
            .addOnFailureListener { exception ->
                Log.e(TAG, exception.message)
            }
    }

    private fun getAllUsers() {
        db.collection("users")
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot) {
                    Log.e(TAG, "${document.id} => ${document.data}")
                    Log.e(TAG, "${document.id} => ${document.getString("name")}")
                }

            }
            .addOnFailureListener { exception ->
                Log.e(TAG, exception.message)
            }
    }

    private fun getAllUsersByLimit() {
        //db.collection("users").limit(2).orderBy("name")
        db.collection("users").limit(2).orderBy("name", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot) {
                    Log.e(TAG, "${document.id} => ${document.data}")
                    //Log.e(TAG,"${document.id} => ${document.getString("name")}")
                }

            }
            .addOnFailureListener { exception ->
                Log.e(TAG, exception.message)
            }
    }

    private fun getAllUsersByWhere() {
        //db.collection("users").whereGreaterThan("level",3)
        db.collection("users").whereGreaterThanOrEqualTo("level", 3)
            .orderBy("level", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot) {
                    Log.e(TAG, "${document.id} => ${document.data}")
                }

            }
            .addOnFailureListener { exception ->
                Log.e(TAG, exception.message)
            }
    }

    private fun deleteUserById(id: String) {
        db.collection("users").document(id)
            .delete()
            .addOnSuccessListener {
                Log.e(TAG, "User deleted successfully")
            }
            .addOnFailureListener { exception ->
                Log.e(TAG, exception.message)
            }
    }

    private fun deleteUserEmailField(id:String){
        db.collection("users").document(id)
            .update("email",FieldValue.delete())
            .addOnSuccessListener {
                Log.e(TAG, "User Email deleted successfully")
            }
            .addOnFailureListener {exception ->
                Log.e(TAG, exception.message)
            }
    }
    // Access a Cloud Firestore instance from your Activity
    private fun GetAllProducts() {

        db.collection("Products")
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot){
                    val name:String = document.get("name") as String
                    val price:Double = document.get("price") as Double
                    val quantity:Int = document.get("quantity") as Int
                    val category:String = document.get("category") as String
                    val id = document.id
                    val p = Product(id,name ,price ,quantity ,category)
                    product.add(p)
                }
            }.addOnFailureListener{exception ->
                Log.e("ttr",exception.message!!)
            }
    }
    class Product(val  id:String ,val name:String, val price:Double, val quantity:Int, val category:String) {
    }
}






