package com.example.loginripa.activities.data.entity

import android.os.Parcel
import android.os.Parcelable

data class Food(
    var id: Int,
    var summary: String,
    var title: String,
    var image: String,
    var pricePerServing: Double,
    var glutenFree: Boolean,
    var extendedIngredients: List<Ingredient>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readDouble(),
        parcel.readByte() != 0.toByte(),
        extendedIngredients = parcel.createTypedArrayList(Ingredient.CREATOR) ?: ArrayList()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(summary)
        parcel.writeString(title)
        parcel.writeString(image)
        parcel.writeDouble(pricePerServing)
        parcel.writeByte(if (glutenFree) 1 else 0)
        parcel.writeTypedList(extendedIngredients)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Food> {
        override fun createFromParcel(parcel: Parcel): Food {
            return Food(parcel)
        }

        override fun newArray(size: Int): Array<Food?> {
            return arrayOfNulls(size)
        }
    }
}

data class Ingredient(
    var originalName: String,
    var original: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(originalName)
        parcel.writeString(original)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Ingredient> {
        override fun createFromParcel(parcel: Parcel): Ingredient {
            return Ingredient(parcel)
        }

        override fun newArray(size: Int): Array<Ingredient?> {
            return arrayOfNulls(size)
        }
    }
}