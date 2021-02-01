package com.example.dbserviseaplication.models

import android.os.Parcel
import android.os.Parcelable

class model (var id:Int, var name: String?, var password: String? , var email: String? , var phonenumber: String?) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()

    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(password)
        parcel.writeString(email)
        parcel.writeString(phonenumber)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<model> {
        override fun createFromParcel(parcel: Parcel): model {
            return model(parcel)
        }

        override fun newArray(size: Int): Array<model?> {
            return arrayOfNulls(size)
        }

        val COL_ID = "id"
        val COL_NAME = "name"
        val COL_PASSWORD = "password"
        val COL_EMAIL = "email"
        val COL_PHONENUMBER = "phonenumber"

        val TABLE_NAME = "people"
        val TABLE_CREATE = "create table $TABLE_NAME ($COL_ID integer primary key autoincrement," +
                "$COL_NAME text not null, $COL_PASSWORD text not null, $COL_EMAIL text not null,$COL_PHONENUMBER text)"
    }
}


