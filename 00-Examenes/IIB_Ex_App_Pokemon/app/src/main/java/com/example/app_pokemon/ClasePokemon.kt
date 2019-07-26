package com.example.app_pokemon

import android.os.Parcel
import android.os.Parcelable

class ClasePokemon(
    private var idPokemon: Int,
    private var nombrePokemon: String,
    private var poderUno: String,
    private var poderDos: String,
    private var fechaCaptura: String,
    private var nivel: Int,
    private var latitud: String,
    private var longitud: String,
    private var fkEntrenador: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idPokemon)
        parcel.writeString(nombrePokemon)
        parcel.writeString(poderUno)
        parcel.writeString(poderDos)
        parcel.writeString(fechaCaptura)
        parcel.writeInt(nivel)
        parcel.writeString(latitud)
        parcel.writeString(longitud)
        parcel.writeInt(fkEntrenador)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ClasePokemon> {
        override fun createFromParcel(parcel: Parcel): ClasePokemon {
            return ClasePokemon(parcel)
        }

        override fun newArray(size: Int): Array<ClasePokemon?> {
            return arrayOfNulls(size)
        }
    }
}