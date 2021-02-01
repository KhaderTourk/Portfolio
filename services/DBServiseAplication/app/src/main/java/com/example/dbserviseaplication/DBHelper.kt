package com.example.dbserviseaplication

import android.app.Activity
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.dbserviseaplication.models.model

class DBHelper (activity: Activity) :
SQLiteOpenHelper(activity, DATABASE_NAME, null, DATABASE_VERSION) {

    private val db: SQLiteDatabase = this.writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(model.TABLE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("Drop table if exists ${model.TABLE_NAME}")
        onCreate(db)
    }


    fun insertPerson(name: String, password: String , email: String,phonenumber: String): Boolean {
        val cv = ContentValues()
        cv.put(model.COL_NAME, name)
        cv.put(model.COL_PASSWORD, password)
        cv.put(model.COL_EMAIL, email)
        cv.put(model.COL_PHONENUMBER, phonenumber)
        return db.insert(model.TABLE_NAME, null, cv) > 0
    }

    fun getAllPerson(): ArrayList<model> {
        val data = ArrayList<model>()
        val c =
            db.rawQuery("select * from ${model.TABLE_NAME} order by ${model.COL_ID} desc", null)
        c.moveToFirst()
        while (!c.isAfterLast) {
            val p = model(c.getInt(0), c.getString(1),  c.getString(2),  c.getString(3), c.getString(4))
            data.add(p)
            c.moveToNext()
        }
        c.close()
        return data
    }



    companion object {
        val DATABASE_NAME = "newDb"
        val DATABASE_VERSION = 1

    }
}