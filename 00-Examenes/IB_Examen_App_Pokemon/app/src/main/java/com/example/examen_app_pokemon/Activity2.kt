package com.example.examen_app_pokemon

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import kotlinx.android.synthetic.main.activity_2.*

class Activity2 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)
        val parentLayout = findViewById<View>(android.R.id.content)
        val usuario: Usuario? = intent.getParcelableExtra("usuario")
        val user = usuario?.nombreUsuario
        mostrarSnackbar(parentLayout, "Bienvenido $user !")

        btn_g_entrenador.setOnClickListener {
            irAListarEntrenadores(usuario)
        }
        btn_Nuevo_Entrenador.setOnClickListener {
            irACrearEntrenador(usuario)
        }
    }

    private fun irAListarEntrenadores(usuario: Usuario?) {
        val intentExplicito = Intent(
            this,
            ListaEntrenadores::class.java
        )
        intentExplicito.putExtra("usuario", usuario)
        startActivity(intentExplicito)
    }

    private fun irACrearEntrenador(usuario: Usuario?) {
        val intentExplicito = Intent(
            this,
            CrearEntrenador::class.java
        )
        intentExplicito.putExtra("usuario", usuario)
        startActivity(intentExplicito)
    }

    // Mostrar un snckbar con el nombre y la posición
    private fun mostrarSnackbar(view: View, texto: String) {
        Snackbar.make(view, texto, Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
        //Snackbar.make
    }

}
