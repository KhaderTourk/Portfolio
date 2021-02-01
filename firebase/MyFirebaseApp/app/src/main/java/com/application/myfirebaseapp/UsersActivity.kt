package com.application.myfirebaseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_users.*
import kotlinx.android.synthetic.main.user_item.view.*

class UsersActivity : AppCompatActivity() {
    var db: FirebaseFirestore? = null
    var adapter: FirestoreRecyclerAdapter<User, UserViewHolder>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        db = Firebase.firestore

        getAllUser()

    }

    private fun getAllUser() {
        val query = db!!.collection("users")
        val options =
            FirestoreRecyclerOptions.Builder<User>().setQuery(query, User::class.java).build()

        adapter = object : FirestoreRecyclerAdapter<User, UserViewHolder>(options) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
                var view = LayoutInflater.from(this@UsersActivity)
                    .inflate(R.layout.user_item, parent, false)
                return UserViewHolder(view)
            }

            override fun onBindViewHolder(holder: UserViewHolder, position: Int, model: User) {
                holder.name.text = model.name
                holder.email.text = model.email
                holder.phone.text = model.phone
                holder.address.text = model.address
                Glide.with(this@UsersActivity).load(model.image).into(holder.image)
                
            }


        }

        all_user_recycle.layoutManager = LinearLayoutManager(this)
        all_user_recycle.adapter = adapter

    }

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name = view.tv_name
        var email = view.tv_email
        var phone = view.tv_phone
        var address = view.tv_address
        var image = view.image_profile
    }

    override fun onStart() {
        super.onStart()
        adapter!!.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter!!.stopListening()
    }
}
