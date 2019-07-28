package com.example.app_pokemon

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_crear_pokemon.*

class CrearPokemon : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_pokemon)
        txv_cp_idEntrenador.text = intent.getStringExtra("idEntrenador").toString()

        btn_guardarPokemon.setOnClickListener {
            crearPokemon()
        }
    }

    private fun crearPokemon() {
        val nombre = txv_cp_nombrePokemon.text.toString().trim()
        val poderuno = txv_cp_poderuno.text.toString().trim()
        val poderdos = txv_cp_poderdos.text.toString().trim()
        val fecha = txv_cp_fecha_captura.text.toString().trim()
        val nivel = txv_cp_nivel.text.toString().trim()
        val latitud = txv_cp_latitud.text.toString().trim()
        val longitud = txv_cp_longitud.text.toString().trim()
        val fkEntrenador = Integer.valueOf(txv_cp_idEntrenador.text.toString().trim())

        if (nombre == "" || poderuno == "" || poderdos == "" || fecha == "" ||
            nivel == "" || longitud == "" || latitud == ""
        ) {
            Toast.makeText(
                applicationContext,
                "Complete el formulario",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            val nuevoPokemon = ClasePokemonParcelable(
                0, nombre, poderuno, poderdos, fecha, Integer.valueOf(nivel), latitud, longitud, fkEntrenador
            )
            conectarBackendPost(nuevoPokemon)
        }

    }

    private fun conectarBackendPost(nuevoPokemon: ClasePokemonParcelable) {

        val url = ClaseServidorBackend.getURL("pokemon")
        val pokemonACrear = listOf(
            "nombrePokemon" to nuevoPokemon.nombrePokemon,
            "poderUno" to nuevoPokemon.poderUno,
            "poderDos" to nuevoPokemon.poderDos,
            "fechaCaptura" to nuevoPokemon.fechaCaptura,
            "nivel" to nuevoPokemon.nivel,
            "latitud" to nuevoPokemon.latitud,
            "longitud" to nuevoPokemon.longitud,
            "fkEntrenador" to nuevoPokemon.fkEntrenador
        )

        Log.i("http", "Mi URL: $url")
        url.httpPost(pokemonACrear)
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http", "Error Post Pokemon: ${ex.message}")
                    }
                    is Result.Success -> {
                        Log.i("http", "Pokemon creado\n $request \n Response $response")
                        volverAmenu()
                    }
                }
            }
    }

    private fun volverAmenu(){
        startActivity(
            Intent(this,
                ListViewPokemon::class.java )
                .putExtra(
                    "pokemonCreado",
                    "${DatosUsuario.obtenerUsuarioActual().nombreusuario} ha creado un nuevo pokemon")
                .putExtra("idEntrenadorActual", txv_cp_idEntrenador.text.toString())
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
    }
}
