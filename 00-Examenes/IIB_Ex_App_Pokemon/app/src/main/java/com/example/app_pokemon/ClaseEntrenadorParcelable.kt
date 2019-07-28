package com.example.app_pokemon

import android.os.Parcel
import android.os.Parcelable

class ClaseEntrenadorParcelable(
    var id: Int,
    var nombreEntrenador: String,
    var apellidoEntrenador: String,
    var fechaNac: String,
    var medallas: Int,
    var campeonActual: Boolean

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readByte() != 0.toByte()

    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombreEntrenador)
        parcel.writeString(apellidoEntrenador)
        parcel.writeString(fechaNac)
        parcel.writeInt(medallas)
        parcel.writeByte(if (campeonActual) 1 else 0)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ClaseEntrenadorParcelable> {
        override fun createFromParcel(parcel: Parcel): ClaseEntrenadorParcelable {
            return ClaseEntrenadorParcelable(parcel)
        }

        override fun newArray(size: Int): Array<ClaseEntrenadorParcelable?> {
            return arrayOfNulls(size)
        }
    }
}