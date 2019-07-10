package com.example.diamanond_manager

import android.os.Parcel
import android.os.Parcelable

class DiamanteParcelable(
    var id: Int,
    var nombre: String,
    var quilate: Double,
    var precio: Double,
    var claridad: String,
    var corte: String,
    var color: String,
    var pais: String,
    var imagen: Int

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeDouble(quilate)
        parcel.writeDouble(precio)
        parcel.writeString(claridad)
        parcel.writeString(corte)
        parcel.writeString(color)
        parcel.writeString(pais)
        parcel.writeInt(imagen)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "$nombre\n$quilate\n$precio\n$claridad\n$corte\n$color\n$pais"
    }

    companion object CREATOR : Parcelable.Creator<DiamanteParcelable> {
        override fun createFromParcel(parcel: Parcel): DiamanteParcelable {
            return DiamanteParcelable(parcel)
        }

        override fun newArray(size: Int): Array<DiamanteParcelable?> {
            return arrayOfNulls(size)
        }

    }
}