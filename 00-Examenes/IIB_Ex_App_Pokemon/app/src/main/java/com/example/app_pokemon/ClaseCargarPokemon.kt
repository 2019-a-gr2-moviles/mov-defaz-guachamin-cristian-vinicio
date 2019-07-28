package com.example.app_pokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_clase_cargar_pokemon.*

class ClaseCargarPokemon : AppCompatActivity() {

    private var bddPokemones = arrayListOf<ClasePokemones>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clase_cargar_pokemon)
        bddPokemones = cargarCoordenadasPokemones()

        bt_cargar_pokemones.setOnClickListener {
            enviarPokemonesAMap()
        }

    }

    private fun cargarCoordenadasPokemones(): ArrayList<ClasePokemones>{
        val url = ClaseServidorBackend.getURL("pokemon")
        Log.i("http", "Mi URL: $url")
        val pokemones = arrayListOf<ClasePokemones>()
        url.httpGet().responseString {_, _, result ->
            when (result) {
                is Result.Failure -> {
                    val ex = result.getException()
                    Log.i("http", "Error get pokemones: ${ex.message}")
                }
                is Result.Success -> {
                    val data = result.get()
                    Log.i("http", data)
                    val datosParseados = Klaxon().parseArray<ClasePokemones>(data)
                    datosParseados!!.forEach{
                        pokemones.add(it)
                        Log.i("http", "${it.id} - ${it.nombrePokemon}")
                    }
                }
            }
        }
        return pokemones
    }

    private fun enviarPokemonesAMap(){
        val bddPokemonesPar = arrayListOf<ClasePokemonParcelable>()
        bddPokemones.forEach {
            val datosPokemon = ClasePokemonParcelable(
                it.id,
                it.nombrePokemon,
                it.poderUno,
                it.poderDos,
                it.fechaCaptura,
                it.nivel,
                it.latitud,
                it.longitud,
                0
            )
            bddPokemonesPar.add(datosPokemon)
        }
        val intentExplicito = Intent(
            this,
            MapsPokemon::class.java
        )
        intentExplicito.putExtra("listaPokemon",bddPokemonesPar)
        startActivity(intentExplicito)
    }


}
