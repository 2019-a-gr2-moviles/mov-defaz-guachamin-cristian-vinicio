package com.example.examen_app_pokemon

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_gestion_pokemon.*
import java.text.SimpleDateFormat

class GestionPokemon : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestion_pokemon)
        val pokemon: Pokemon = intent.getParcelableExtra("pokemon")
        val usuario: Usuario = intent.getParcelableExtra("usuario")
        val entrenador: Entrenador = intent.getParcelableExtra("entrenador")
        cargardatosPokemon(pokemon)

        btn_actualizar.setOnClickListener {
            Toast.makeText(
                applicationContext,
                "${usuario.nombreUsuario} ha actualizado el pokemon", Toast.LENGTH_SHORT
            ).show()
            actualizarPokemon(pokemon, entrenador)
        }

        btn_eliminar.setOnClickListener {
            Toast.makeText(
                applicationContext,
                "${usuario.nombreUsuario} ha eliminado el pokemon", Toast.LENGTH_SHORT
            ).show()
            Pokemon.eliminarPokemon(pokemon)
        }

    }

    private fun cargardatosPokemon(pokemon: Pokemon){
        val simpleDateFormat = SimpleDateFormat("dd-mm-yyyy")
        txt_Pok_nombre.setText(pokemon.nombrePokemon)
        txt_pok_poder1.setText(pokemon.poderEspecialUno)
        txt_pok_poder2.setText(pokemon.poderEspecialDos)
        txt_FechaCaptura.setText(simpleDateFormat.format(pokemon.fechaCaptura)).toString()
        txt_pok_nivel.setText(pokemon.nivel.toString())
    }

    private fun actualizarPokemon(pokemonActual: Pokemon, entrenador: Entrenador){
        val numPokemon = pokemonActual.numPokemon
        val nomPokemon = txt_Pok_nombre.text.toString()
        val poder1 = txt_pok_poder1.text.toString()
        val poder2 = txt_pok_poder2.text.toString()
        val fecha = Pokemon.retornarFecha(txt_FechaCaptura.text.toString())
        val nivel = Integer.parseInt(txt_pok_nivel.text.toString())
        val id = entrenador.id
        val nuevoPokemon =  Pokemon(
           numPokemon,nomPokemon,poder1,poder2,fecha,nivel,id
        )
        Pokemon.actualizarPokemon(pokemonActual, nuevoPokemon)
    }
}
