package com.example.loginripa.activities.data.entity

import android.os.Parcel
import android.os.Parcelable

class User(
    var name: String? = "",
    var surname: String? = "",
    var username: String? = "",
    var email: String? = "",
    var password: String? = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeString(name)
        p0.writeString(surname)
        p0.writeString(username)
        p0.writeString(email)
        p0.writeString(password)
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}