package com.example.shazam

import android.os.Parcel
import android.os.Parcelable

class ParcelableMusica (
    val titulo: String,
    val autor: String,
    val album: String):Parcelable
{
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!

    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(titulo)
        parcel.writeString(autor)
        parcel.writeString(album)
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