package com.example.a04_android_parselabel

import android.os.Parcelable
import android.os.Parcel
import java.util.*

class Usuario (var nombre: String,
               var edad:Int,
               var fechaNacimiento: Date,
               var sueldo: Double): Parcelable {
    // val nombre: String -> propiedad
    // nombre: String -> parametro

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readSerializable() as Date,
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeInt(edad)
        parcel.writeSerializable(fechaNacimiento)
        // Parcelable no sabe como leer, por ello se lo hace manualmente
        parcel.writeDouble(sueldo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : android.os.Parcelable.Creator<Usuario> {
        override fun createFromParcel(parcel: Parcel): Usuario {
            return Usuario(parcel)
        }

        override fun newArray(size: Int): Array<Usuario?> {
            return arrayOfNulls(size)
        }
    }
}
