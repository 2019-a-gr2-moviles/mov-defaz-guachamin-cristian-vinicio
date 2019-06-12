package com.example.examen_app_pokemon

import android.os.Parcel
import android.os.Parcelable
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Pokemon (
    var numPokemon: Int,
    var nombrePokemon: String,
    var poderEspecialUno: String,
    var poderEspecialDos: String,
    var fechaCaptura: Date,
    var nivel: Int,
    var entrenadorId: String
    ): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readSerializable() as Date,
        parcel.readInt(),
        parcel.readString()!!
    )
    constructor():this(
        0,
        "",
        "",
        "",
        Entrenador.retornarFecha("01-01-1999"),
        0,
        ""
    )

    override fun toString():String {
        return "$numPokemon Nombre: $nombrePokemon"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(numPokemon)
        parcel.writeString(nombrePokemon)
        parcel.writeString(poderEspecialUno)
        parcel.writeString(poderEspecialDos)
        parcel.writeSerializable(fechaCaptura)
        parcel.writeInt(nivel)
        parcel.writeString(entrenadorId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pokemon> {
        private val listaPokemon = arrayListOf(
            Pokemon(
                1,
                "Pikachu",
                "Electricidad",
                "Pararayos",
                retornarFecha("06-04-2015"),
                2,
                "CaS"
            ),
            Pokemon(
                2,
                "Bulbasaur",
                "Espesura",
                "Clorofila",
                retornarFecha("01-11-2015"),
                2,
                "ReX"
            ),
            Pokemon(
                3,
                "Charmander",
                "Mar Llamas",
                "Poder Solar",
                retornarFecha("26-07-2015"),
                2,
                "ReX"
            ),
            Pokemon(
                4,
                "Charizard",
                "Mar Llamas",
                "Poder Solar",
                retornarFecha("11-07-2016"),
                2,
                "ClI"
            )
        )

        fun cargarPokemonesPorEntrenador(idEntrenador: String): ArrayList<Pokemon>{
            return retornarListaPokemon().filter {eachPokemon ->
                eachPokemon.entrenadorId == idEntrenador
            } as ArrayList<Pokemon>
        }

        fun cargarDatosPokemon(nombrePokemon: String): Pokemon{
            var pokemon = Pokemon()
            retornarListaPokemon().forEach{ eachPokemon ->
                if(eachPokemon.nombrePokemon == nombrePokemon){
                    pokemon =  eachPokemon
                }
            }
            return pokemon
        }

        override fun createFromParcel(parcel: Parcel): Pokemon {
            return Pokemon(parcel)
        }

        override fun newArray(size: Int): Array<Pokemon?> {
            return arrayOfNulls(size)
        }

        fun retornarListaPokemon(): ArrayList<Pokemon>{
            return listaPokemon
        }

        fun agregarPokemon(numPokemon: Int, nombrePokemon: String, poder1: String, poder2: String, fechaCap: Date, nivel: Int, idEnt: String){
            Pokemon.listaPokemon.add( Pokemon(numPokemon, nombrePokemon, poder1,  poder2, fechaCap, nivel, idEnt))
        }

        fun eliminarPokemon(pokemon: Pokemon){
            listaPokemon.remove(pokemon)
        }

        fun actualizarPokemon(pokemonAnt: Pokemon, pokemonNuevo: Pokemon){
            eliminarPokemon(pokemonAnt)
            listaPokemon.add(pokemonNuevo)
        }

        fun retornarFecha(fecha:String): Date {
            val formatter = SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH)
            return formatter.parse(fecha)
        }
    }

}