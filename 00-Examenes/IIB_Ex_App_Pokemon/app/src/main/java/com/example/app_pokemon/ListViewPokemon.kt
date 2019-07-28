package com.example.app_pokemon

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_list_view_pokemon.*

class ListViewPokemon : AppCompatActivity() {

    private var bddPokemones = arrayListOf<ClasePokemones>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view_pokemon)
       // val id = intent.getStringExtra("idEntrenador")
      //  bddPokemones = obtenerListaPokemones(Integer.valueOf(id))
       // Log.i("http","identrenador: $id")
        cargarIdEntrenador()


        txv_cp_titulo.setOnClickListener {
            val listaEntrenadores = arrayListOf<String>()
            bddPokemones.forEach {
                listaEntrenadores.add("${it.id} - ${it.nombrePokemon} Poder especial: ${it.poderUno}")
            }
            crearAdaptador(listaEntrenadores)
        }
    }

    private fun mostrarToastConUsuario(){
        val accionRealizada = intent.getStringExtra("pokemonActualizado")
        if(accionRealizada != null){
            Toast.makeText(applicationContext,
                accionRealizada,
                Toast.LENGTH_SHORT).show()
        }
    }

    private fun cargarIdEntrenador(){
        val id = intent.getStringExtra("idEntrenador")
        if(id != null){
            val id3 = intent.getStringExtra("idEntrenador")
            bddPokemones = obtenerListaPokemones(Integer.valueOf(id3))
            Log.i("http","id3: $id3")
        }else{
            val id2 = intent.getStringExtra("idEntrenadorActual")
            bddPokemones = obtenerListaPokemones(Integer.valueOf(id2))
            mostrarToastConUsuario()
            Log.i("http","id2: $id2")
        }
    }

    private fun crearAdaptador(listaPokemon: ArrayList<String>){

        val adaptadorPokemon = ArrayAdapter(this,
            android.R.layout.simple_list_item_1,
            listaPokemon
        )
        lv_pokemones.adapter = adaptadorPokemon
        lv_pokemones.onItemClickListener = AdapterView
            .OnItemClickListener { parent, _, position, _ ->
                val itemSeeccionado = parent.getItemAtPosition(position).toString()
                buscarPokemonPorId(itemSeeccionado.split("-")[0].trim().toInt())?.let {
                    solicitarDetallesDePokemon(
                        it
                    )
                }
                Toast.makeText(
                    applicationContext,
                    itemSeeccionado,
                    Toast.LENGTH_SHORT
                ).show()
            }
        adaptadorPokemon.notifyDataSetChanged()
    }

    private fun obtenerListaPokemones(idPokemon: Int): ArrayList<ClasePokemones>{

        val url = ClaseServidorBackend.getURL("pokemon?fkEntrenador=$idPokemon")
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

    private fun solicitarDetallesDePokemon(pokemon: ClasePokemones){
        val datosPokemon = ClasePokemonParcelable(
            pokemon.id,
            pokemon.nombrePokemon,
            pokemon.poderUno,
            pokemon.poderDos,
            pokemon.fechaCaptura,
            pokemon.nivel,
            pokemon.latitud,
            pokemon.longitud,
            0
        )
        val intentExplicito = Intent(
            this,
            VerPokemon::class.java
        )
        intentExplicito.putExtra("pokemon",datosPokemon)
        startActivity(intentExplicito)
    }

    private fun buscarPokemonPorId(id: Int): ClasePokemones? {
        bddPokemones.forEach {
            if(it.id == id){
                return it
            }
        }
        return null
    }

}
