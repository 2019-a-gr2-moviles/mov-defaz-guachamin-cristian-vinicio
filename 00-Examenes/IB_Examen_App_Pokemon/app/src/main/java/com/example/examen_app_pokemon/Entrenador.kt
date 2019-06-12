package com.example.examen_app_pokemon

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.synthetic.main.activity_lista_entrenadores.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Entrenador(
    var nombres: String,
    var apellidos: String,
    var fechaNacimiento: Date,
    var numeroMedallas: Int,
    var campeonActual: Boolean,
    var id: String
):Parcelable{

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readSerializable() as Date,
        parcel.readInt() ,
        parcel.readSerializable() as Boolean,
        parcel.readString()
    )

    constructor():this(
        "",
        "",
        retornarFecha("01-01-1999"),
        0,
        false,
        ""
        )

    override fun toString():String {
        return "$nombres $apellidos ${nombres.substring(0,2)}${apellidos.substring(0,1)}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombres)
        parcel.writeString(apellidos)
        parcel.writeSerializable(fechaNacimiento)
        parcel.writeInt(numeroMedallas)
        parcel.writeSerializable(campeonActual)
        parcel.writeString(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Entrenador> {
        override fun createFromParcel(parcel: Parcel): Entrenador {
            return Entrenador(parcel)
        }

        override fun newArray(size: Int): Array<Entrenador?> {
            return arrayOfNulls(size)
        }
        private val listaEntrenadores = arrayListOf(
            Entrenador(
            "Caith",
            "Sith",
            retornarFecha("12-11-1995"),
          //      "12-11-1995",
            6,
            false,
            "CaS"),
            Entrenador(
                "Cloud",
                "III",
                retornarFecha("21-12-1999"),
             //   "21-12-1999",
                7,
                false,
                "ClI"),
            Entrenador(
                "Crew",
                "Mad",
                retornarFecha("17-09-1995"),
             //   "17-09-1995",
                3,
                false,
                "CrM"),
            Entrenador(
                "Rey",
                "Xion",
                retornarFecha("04-11-1997"),
               // "17-09-1995",
                7,
                true,
                "ReX")
        )

        fun cargarDatosEntrenador(idEntrenador: String): Entrenador {
           var entrenador = Entrenador("Rey","Xion", retornarFecha("11-11-2018"),2,false,"Rex")
            retornarListaEntrenador().forEach{eachEntrenador ->
                if(eachEntrenador.id == idEntrenador){
                    entrenador =  eachEntrenador
                }
            }
            return entrenador
        }

        fun agregarEntrenador(nom: String, ape: String, med: Int, fechaNac: Date){
            listaEntrenadores.add( Entrenador(nom, ape, fechaNac, med, false, nom.substring(0,2)+ape.substring(0,1)))
        }

        fun retornarListaEntrenador(): ArrayList<Entrenador>{
            return listaEntrenadores
        }

        fun eliminarEntrenador(entrenador: Entrenador){
            listaEntrenadores.remove(entrenador)
        }

        fun actualizarEntrenador(entrenadorAnt: Entrenador, entrenadorNuevo: Entrenador){
            eliminarEntrenador(entrenadorAnt)
            listaEntrenadores.add(entrenadorNuevo)
        }

        fun retornarFecha(fecha:String): Date {
            val formatter = SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH)
            return formatter.parse(fecha)
        }

    }


}

