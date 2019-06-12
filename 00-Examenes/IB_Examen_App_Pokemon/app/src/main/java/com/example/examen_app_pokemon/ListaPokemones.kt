package com.example.examen_app_pokemon

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_lista_entrenadores.*
import kotlinx.android.synthetic.main.activity_lista_pokemones.*
import java.text.SimpleDateFormat
import java.util.*

class ListaPokemones : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_pokemones)
        val entrenador: Entrenador = intent.getParcelableExtra("entrenador")
        val usuario: Usuario = intent.getParcelableExtra("usuario")

    val adapter = ArrayAdapter<Pokemon>(this,
        android.R.layout.simple_list_item_1,
        Pokemon.cargarPokemonesPorEntrenador(entrenador.id))
        
        adapter.notifyDataSetChanged()
    lv_listaPokemon.adapter = adapter



    lv_listaPokemon.onItemClickListener = AdapterView
        .OnItemClickListener { parent, view, position, _ ->
            val nombrePokemon = parent.getItemAtPosition(position).toString().split(" ")[2]
            val pokemon = Pokemon.cargarDatosPokemon(nombrePokemon)
         //   Pokemon.cargarPokemonesPorEntrenador(usuario.id)
            Toast.makeText(
                applicationContext,
                "${usuario.nombreUsuario} pokemon: $nombrePokemon", Toast.LENGTH_SHORT
            ).show()
            irAGestionarPokemon(pokemon, usuario, entrenador)
        }
    }

    private fun irAGestionarPokemon(pokemon: Pokemon, usuario: Usuario, entrenador: Entrenador){
        val intentExplicito = Intent(
            this,
            GestionPokemon::class.java
        )
        intentExplicito.putExtra("pokemon",pokemon)
        intentExplicito.putExtra("usuario",usuario)
        intentExplicito.putExtra("entrenador",entrenador)
        startActivity(intentExplicito)
    }
}
