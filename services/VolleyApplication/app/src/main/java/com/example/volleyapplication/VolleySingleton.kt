package com.example.volleyapplication

import android.app.Application
import android.text.TextUtils
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class VolleySingleton: Application() {
    val TAG = "hzm"
    private var mRequestQueue:RequestQueue? = null

    companion object {
        @Volatile
        private var INSTANCE: VolleySingleton? = null

        fun getInstance(): VolleySingleton? {
            return INSTANCE
        }
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE=this
    }
    private fun getRequestQueue():RequestQueue?{
        if (mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(applicationContext)
        }
        return mRequestQueue
    }



    fun <T> addToRequestQueue(req: Request<T>,tag :String) {
        req.tag = if (TextUtils.isEmpty(tag))TAG else tag
        getRequestQueue()!!.add(req)
    }
fun <T> addToRequestQueue(req:Request<T>){
    req.tag = TAG
    getRequestQueue()!!.add(req)
}
fun cancelPendingRequests(tag:Any?){
    if (mRequestQueue != null){
        mRequestQueue!!.cancelAll(tag)
    }
}

}