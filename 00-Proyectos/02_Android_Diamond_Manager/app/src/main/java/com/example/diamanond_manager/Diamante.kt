package com.example.diamanond_manager

import android.os.Parcel
import android.os.Parcelable

class Diamante (
        var nombre: String,
        var claridad: String,
        var color: String,
        var corte: String,
        var quilate: Double,
        var precio: Double,
        var paisOrigen: String
):Parcelable
{
    constructor(parcel: Parcel) : this(
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readDouble(),
            parcel.readDouble(),
            parcel.readString()!!)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(claridad)
        parcel.writeString(color)
        parcel.writeString(corte)
        parcel.writeDouble(quilate)
        parcel.writeDouble(precio)
        parcel.writeString(paisOrigen)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Diamante> {
        override fun createFromParcel(parcel: Parcel): Diamante {
            return Diamante(parcel)
        }

        override fun newArray(size: Int): Array<Diamante?> {
            return arrayOfNulls(size)
        }
    }

}