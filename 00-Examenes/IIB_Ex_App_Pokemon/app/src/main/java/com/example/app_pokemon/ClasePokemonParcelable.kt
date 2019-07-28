package com.example.app_pokemon

import android.os.Parcel
import android.os.Parcelable

class ClasePokemonParcelable(
    var idPokemon: Int,
    var nombrePokemon: String,
    var poderUno: String,
    var poderDos: String,
    var fechaCaptura: String,
    var nivel: Int,
    var latitud: String,
    var longitud: String,
    var fkEntrenador: Int
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

    companion object CREATOR : Parcelable.Creator<ClasePokemonParcelable> {
        override fun createFromParcel(parcel: Parcel): ClasePokemonParcelable {
            return ClasePokemonParcelable(parcel)
        }

        override fun newArray(size: Int): Array<ClasePokemonParcelable?> {
            return arrayOfNulls(size)
        }
    }
}