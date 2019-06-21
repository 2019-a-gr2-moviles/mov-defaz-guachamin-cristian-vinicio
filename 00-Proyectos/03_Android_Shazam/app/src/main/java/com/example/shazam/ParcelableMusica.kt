package com.example.shazam

import android.os.Parcel
import android.os.Parcelable

class ParcelableMusica (
    val titulo: String,
    val artista: String,
    val album: String,
    val idCancion: Int):Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(titulo)
        parcel.writeString(artista)
        parcel.writeString(album)
        parcel.writeInt(idCancion)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ParcelableMusica> {
        override fun createFromParcel(parcel: Parcel): ParcelableMusica {
            return ParcelableMusica(parcel)
        }

        override fun newArray(size: Int): Array<ParcelableMusica?> {
            return arrayOfNulls(size)
        }
    }
}