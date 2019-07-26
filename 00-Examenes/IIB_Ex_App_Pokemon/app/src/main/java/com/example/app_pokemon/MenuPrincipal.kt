package com.example.app_pokemon

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_menu_principal.*

class MenuPrincipal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)
        mostrarToastConUsuario()

        btn_mp_gestionar_entrenador.setOnClickListener {
            irAListaEntrenadores()
        }

        btn_mp_crear_entrenador.setOnClickListener {
            irACrearEntrenador()
        }
        btn_mp_ver_mapaPokemon.setOnClickListener {
            irAMapaDeHijos()
        }

    }

    private fun mostrarToastConUsuario(){
        Toast.makeText(applicationContext,
            DatosUsuario.obtenerUsuarioActual().nombreusuario,
            Toast.LENGTH_SHORT).show()
    }

    private fun irAListaEntrenadores(){
        startActivity(
            Intent(this,
                ListViewPokemon::class.java )
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
    }

    private fun irACrearEntrenador(){
        startActivity(
            Intent(this,
                CrearEntrenador::class.java )
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
    }

    private fun irAMapaDeHijos(){
        //
    }


}
