package com.application.myfirebaseapp

import android.app.ProgressDialog
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.vansuita.pickimage.bean.PickResult
import com.vansuita.pickimage.bundle.PickSetup
import com.vansuita.pickimage.dialog.PickImageDialog
import com.vansuita.pickimage.listeners.IPickResult
import kotlinx.android.synthetic.main.activity_profile.*
import java.util.*

class ProfileActivity : AppCompatActivity(), IPickResult {
    var db: FirebaseFirestore? = null
    var storage: FirebaseStorage? = null
    var reference: StorageReference? = null
    lateinit var auth: FirebaseAuth
    lateinit var progressDialog: ProgressDialog
    var path: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        db = Firebase.firestore
        auth = Firebase.auth
        storage = Firebase.storage
        reference = storage!!.reference
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("جاري التحميل")
        progressDialog.setCancelable(false)
        getProfileData()

        image_profile.setOnClickListener {
            PickImageDialog.build(PickSetup()).show(this)
        }


        btn_save.setOnClickListener {
            updateImage(path)
        }

    }

    private fun updateImage(path: String?) {
        db!!.collection("users").whereEqualTo("email", auth.currentUser!!.email).get()
            .addOnSuccessListener { querySnapshot ->
                db!!.collection("users").document(querySnapshot.documents.get(0).id)
                    .update("image", path)
            }.addOnFailureListener { exception ->

            }
    }

    fun getProfileData() {
        db!!.collection("users").get()
            .addOnSuccessListener { querySnapshot ->
                ed_name.setText(querySnapshot.documents.get(0).get("name").toString())
                ed_phone.setText(querySnapshot.documents.get(0).get("phone").toString())
                ed_address.setText(querySnapshot.documents.get(0).get("address").toString())
                ed_email.setText(auth.currentUser!!.email)
            }.addOnFailureListener { exception ->

            }
    }

    override fun onPickResult(r: PickResult?) {
        image_profile.setImageBitmap(r!!.bitmap)
        uploadImage(r.uri)
    }

    private fun uploadImage(uri: Uri?) {
        progressDialog.show()
        reference!!.child("profile/" + UUID.randomUUID().toString()).putFile(uri!!)
            .addOnSuccessListener { taskSnapshot ->
                progressDialog.dismiss()
                taskSnapshot.storage.downloadUrl.addOnSuccessListener { uri ->
                    path = uri.toString()
                }
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener { exception ->

            }
    }

}
