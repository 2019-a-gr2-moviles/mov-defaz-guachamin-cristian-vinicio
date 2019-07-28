package com.example.app_pokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_ver_entrenador.*

class VerEntrenador : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_entrenador)
        obteneryCargarDatosEntrenador()
        mostrarToastConUsuario()

        btn_ver_listaPokemon.setOnClickListener {
            solicitarDatosPokemones(txv_idEntrenador.text.toString())
        }

        btn_crearPokemon.setOnClickListener {
            irACrearPokemon(txv_idEntrenador.text.toString())
        }

        btn_editarEntrenador.setOnClickListener {
            actualizarEntrenador()
        }
    }

    private fun obteneryCargarDatosEntrenador() {
        val entrenadorAMostrar = intent
            .getParcelableExtra<ClaseEntrenadorParcelable>("entrenador")
        txv_idEntrenador.text = entrenadorAMostrar.id.toString()
        txv_nom_Entrenador.setText(entrenadorAMostrar.nombreEntrenador)
        txv_ape_Entrenador.setText(entrenadorAMostrar.apellidoEntrenador)
        txv_fecha_entrenador.setText(entrenadorAMostrar.fechaNac)
        txv_medalla_entrenador.setText(entrenadorAMostrar.medallas.toString())

        if (entrenadorAMostrar.campeonActual) {
            switch_campeon.isChecked = true
        }
    }

    private fun solicitarDatosPokemones(id: String) {
        val intentExplicito = Intent(
            this,
            ListViewPokemon::class.java
        )
        intentExplicito.putExtra("idEntrenador", id)
        startActivity(intentExplicito)

    }

    private fun irACrearPokemon(id: String){
        val intentExplicito = Intent(
            this,
            CrearPokemon::class.java
        )
        intentExplicito.putExtra("idEntrenador", id)
        startActivity(intentExplicito)
    }

    private fun mostrarToastConUsuario(){
        val accionRealizada = intent.getStringExtra("crearPokemon")
        if(accionRealizada != null){
            Toast.makeText(applicationContext,
                accionRealizada,
                Toast.LENGTH_SHORT).show()
        }
    }

    private fun actualizarEntrenador(){
        val id = txv_idEntrenador.text.toString().trim()
        val nombre = txv_nom_Entrenador.text.toString().trim()
        val apellido = txv_ape_Entrenador.text.toString().trim()
        val fechaNac = txv_fecha_entrenador.text.toString().trim()
        val medallas = txv_medalla_entrenador.text.toString().trim()
        var campeon = false
        switch_campeon.setOnCheckedChangeListener{ _, isChecked ->
            if (isChecked) {
                campeon = true
                Log.i("http","Switch encendido")
            }
        }

        if (nombre == "" || apellido == "" || fechaNac == "" || medallas == "") {
            Toast.makeText(
                applicationContext,
                "Complete el formulario",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            val entrenadorActualizado = ClaseEntrenadorParcelable(
                Integer.valueOf(id), nombre, apellido, fechaNac, Integer.valueOf(medallas), campeon
            )
            conectarBackendPut(entrenadorActualizado)
        }

    }

    private fun conectarBackendPut(entrenadorActualizado: ClaseEntrenadorParcelable) {

        val url = ClaseServidorBackend.getURL("entrenador/${entrenadorActualizado.id}")
        Log.i("http", "Mi URL: $url")

        val entrenadorAactualizar = listOf(
            "nombreEntrenador" to entrenadorActualizado.nombreEntrenador,
            "apellidoEntrenador" to entrenadorActualizado.apellidoEntrenador,
            "fechaNac" to entrenadorActualizado.fechaNac,
            "medallas" to entrenadorActualizado.medallas,
            "campeonActual" to entrenadorActualizado.campeonActual
        )

        url.httpPut(entrenadorAactualizar)
            .responseString { _, _, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http", "Error Put Entrenador: ${ex.message}")
                    }
                    is Result.Success -> {
                        Log.i("http", "Entrenador actualizado")
                        volverALista()
                    }
                }
            }
    }

    private fun volverALista(){
        startActivity(
            Intent(this,
                ListViewEntrenador::class.java )
                .putExtra(
                    "crearEntrenador",
                    "${DatosUsuario.obtenerUsuarioActual()} ha creado un nuevo entrenador")
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
    }

}
