package com.example.examen_app_pokemon

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_lista_entrenadores.*

class ListaEntrenadores : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_entrenadores)
        val parentLayout = findViewById<View>(android.R.id.content)
        val usuario: Usuario = intent.getParcelableExtra("usuario")
        val user = usuario.nombreUsuario
        mostrarSnackbar(parentLayout,user)

        val adapter = ArrayAdapter<Entrenador>(this,
            android.R.layout.simple_list_item_1,
            Entrenador.retornarListaEntrenador()
        )

        lv_lista_entrenadores.adapter = adapter

        lv_lista_entrenadores.onItemClickListener = AdapterView
            .OnItemClickListener { parent, view, position, _ ->
                val itemSeeccionado = parent.getItemAtPosition(position).toString()
                val idEntrenador = itemSeeccionado.substring(itemSeeccionado.length-3,itemSeeccionado.length)
                val entrenador:Entrenador = Entrenador.cargarDatosEntrenador(idEntrenador)
                irAGestionarEntrenador(usuario,entrenador)
            }
    }

    private fun irAGestionarEntrenador(usuario: Usuario, idEntrenador: Entrenador){
        val intentExplicito = Intent(
            this,
            GestionEntrenadores::class.java
        )
       intentExplicito.putExtra("usuario",usuario)
       intentExplicito.putExtra("entrenador",idEntrenador)
        startActivity(intentExplicito)
    }

    // Mostrar un snckbar con el nombre y la posición
    private fun mostrarSnackbar(view: View, texto:String){
        Snackbar.make(view, texto, Snackbar.LENGTH_LONG)
            .setAction("Action",null).show()
    }
}


