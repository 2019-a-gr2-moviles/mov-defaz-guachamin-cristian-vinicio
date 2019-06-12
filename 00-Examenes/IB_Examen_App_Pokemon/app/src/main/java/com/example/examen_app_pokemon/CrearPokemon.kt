package com.example.examen_app_pokemon

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_crear_pokemon.*

class CrearPokemon : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_pokemon)
        val entrenador: Entrenador = intent.getParcelableExtra("entrenador")
        val usuario: Usuario = intent.getParcelableExtra("usuario")
        txt_idEntrenador.setText(entrenador.id)
        txt_poke_num.setText((Pokemon.retornarListaPokemon().size +1).toString())
        btn_nuevoPokemon.setOnClickListener {
            crearPokemon(usuario)
        }
    }

    private fun crearPokemon(usuario: Usuario){
        val numPok = Pokemon.retornarListaPokemon().size +1
        val nombrePokemon =  txt_poke_nombre.text.toString()
        val poder1 =  txt_Poke_poder1.text.toString()
        val poder2 =  txt_poke_poder2.text.toString()
        val fechaCap =  Pokemon.retornarFecha(txt_fecha_captura.text.toString())
        val medallas = Integer.valueOf(txt_poke_nivel.text.toString())
        val idEntrenador =  txt_idEntrenador.text.toString()
        Pokemon.agregarPokemon(numPok, nombrePokemon, poder1, poder2, fechaCap, medallas, idEntrenador)
        Toast.makeText(
            applicationContext,
            "${usuario.nombreUsuario} ha creado un nuevo Pokemon: $nombrePokemon", Toast.LENGTH_SHORT
        ).show()
    }
}
