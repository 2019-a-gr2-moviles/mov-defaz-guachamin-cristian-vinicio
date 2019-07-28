package com.example.app_pokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
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
        val accionRealizada = intent.getStringExtra("crearEntrenador")
        if(accionRealizada == null){
            Toast.makeText(applicationContext,
                "Bienvenido de nuevo ${DatosUsuario.obtenerUsuarioActual().nombreusuario}",
                Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(applicationContext,
                accionRealizada,
                Toast.LENGTH_SHORT).show()
        }
    }

    private fun irAListaEntrenadores(){
        startActivity(
            Intent(this,
                ListViewEntrenador::class.java )
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
    }

    private fun irACrearEntrenador(){
        startActivity(
            Intent(this,
                CrearEntrenador::class.java )
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
    }

    private fun irAMapaDeHijos(){
        val intentExplicito = Intent(
            this,
            ClaseCargarPokemon::class.java
        )
        startActivity(intentExplicito)
    }


}