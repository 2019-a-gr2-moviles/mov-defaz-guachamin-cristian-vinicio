package com.example.examen_app_pokemon

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_gestion_entrenadores.*
import java.text.DateFormat
import java.text.SimpleDateFormat

class GestionEntrenadores : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestion_entrenadores)
        val usuario: Usuario = intent.getParcelableExtra("usuario")
        val entrenador: Entrenador = intent.getParcelableExtra("entrenador")
        cargarDatosDeEntrenador(entrenador)

        btn_crearPokemon.setOnClickListener {
            irACrearPokemon(entrenador, usuario)
        }

        btn_gestionarPokemon.setOnClickListener {
            irAListarPokemon(entrenador, usuario)
        }

        btn_actualizarEnt.setOnClickListener {
           Entrenador.eliminarEntrenador(entrenador)
            Toast.makeText(
                applicationContext,
                "${usuario.nombreUsuario} ha actualizado un elemento", Toast.LENGTH_SHORT
            ).show()
            actualizar(entrenador)
        }
        btn_eliminarEnt.setOnClickListener {
            Toast.makeText(
                applicationContext,
                "${usuario.nombreUsuario} ha borrado un elemento", Toast.LENGTH_SHORT
            ).show()
            // Volver atrás
            volverTrasEliminar()
        }

    }

    private fun irACrearPokemon(entrenador: Entrenador, usuario: Usuario){
        val intentExplicito = Intent(
            this,
            CrearPokemon::class.java
        )
        Log.i("entrenador en GestionEnt:","Entrenador: ${entrenador.nombres}")
        intentExplicito.putExtra("entrenador",entrenador)
        intentExplicito.putExtra("usuario",usuario)
        startActivity(intentExplicito)
    }

    private fun irAListarPokemon(entrenador: Entrenador, usuario: Usuario){
        val intentExplicito = Intent(
            this,
            ListaPokemones::class.java
        )
        intentExplicito.putExtra("entrenador",entrenador)
        intentExplicito.putExtra("usuario",usuario)
        startActivity(intentExplicito)

    }
    private fun cargarDatosDeEntrenador(ent: Entrenador){
        val simpleDateFormat = SimpleDateFormat("dd-mm-yyyy")
        txt_ent_nombre.setText(ent.nombres)
        txt_ent_apellido.setText(ent.apellidos)
        txt_ent_id.setText(ent.id)
        txt_ent_fechaNac.setText(simpleDateFormat.format(ent.fechaNacimiento)).toString()
        txt_ent_Medallas.setText(ent.numeroMedallas.toString())
        if(ent.campeonActual){
            switch_campeon.isChecked = true
        }
    }

    private fun volverTrasEliminar(){
        val intentExplicito = Intent(
            this,
            Activity2::class.java
        )
        startActivity(intentExplicito)
    }

    private fun actualizar(entrenador: Entrenador){
        val campeon = switch_campeon.isSelected
        val nuevoEntrenador = Entrenador(
            txt_ent_nombre.text.toString(),
        txt_ent_apellido.text.toString(),
        Entrenador.retornarFecha(txt_ent_fechaNac.text.toString()),
        Integer.valueOf(txt_ent_Medallas.text.toString()),
        campeon,
        txt_ent_id.text.toString())

        Entrenador.actualizarEntrenador(entrenador,nuevoEntrenador)
    }

}
