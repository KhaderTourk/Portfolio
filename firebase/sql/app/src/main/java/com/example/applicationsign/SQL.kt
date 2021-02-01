package com.example.applicationsign

import android.app.Activity
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.applicationsign.models.Person

class SQL(activity: Activity) :
    SQLiteOpenHelper(activity, DATABASE_NAME, null, DATABASE_VERSION) {

    private val db: SQLiteDatabase = this.writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(Person.TABLE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("Drop table if exists ${Person.TABLE_NAME}")
        onCreate(db)
    }

    //==============================================================================================
    //DML

    fun insertPerson(name: String, password: String , email: String,phonenumber: String): Boolean {
        val cv = ContentValues()
        cv.put(Person.COL_NAME, name)
        cv.put(Person.COL_PASSWORD, password)
        cv.put(Person.COL_EMAIL, email)
        cv.put(Person.COL_PHONENUMBER, phonenumber)
        return db.insert(Person.TABLE_NAME, null, cv) > 0
    }

    fun getAllPerson(): ArrayList<Person> {
        val data = ArrayList<Person>()
        val c =
            db.rawQuery("select * from ${Person.TABLE_NAME} order by ${Person.COL_ID} desc", null)
        c.moveToFirst()
        while (!c.isAfterLast) {
            val p = Person(c.getInt(0), c.getString(1),  c.getString(2),  c.getString(3), c.getString(4))
            data.add(p)
            c.moveToNext()
        }
        c.close()
        return data
    }



    companion object {
        val DATABASE_NAME = "Test"
        val DATABASE_VERSION = 1

    }
}