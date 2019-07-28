package com.example.app_pokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_ver_pokemon.*

class VerPokemon : AppCompatActivity() {

    private var idEntrenadorGlobal = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_pokemon)
        obteneryCargarDatosPokemon()

        btn_actualizarPokemon.setOnClickListener {
            actualizarPokemon()
        }
    }

    private fun obteneryCargarDatosPokemon() {

        val pokemonAMostrar = intent
            .getParcelableExtra<ClasePokemonParcelable>("pokemon")
        txv_idPokemon.text = pokemonAMostrar.idPokemon.toString()
        txv_nom_Pokemon.setText(pokemonAMostrar.nombrePokemon)
        txv_vp_poderuno.setText(pokemonAMostrar.poderUno)
        txv_vp_poderdos.setText(pokemonAMostrar.poderDos)
        txv_vp_nivel.setText(pokemonAMostrar.nivel.toString())
        txv_vp_fechaCaptura.setText(pokemonAMostrar.fechaCaptura)
        txv_vp_latitud.setText(pokemonAMostrar.latitud)
        txv_vp_longitud.setText(pokemonAMostrar.longitud)
        idEntrenadorGlobal = pokemonAMostrar.fkEntrenador

    }

    private fun actualizarPokemon() {
        val id = txv_idPokemon.text.toString()
        val nombre = txv_nom_Pokemon.text.toString().trim()
        val poderuno = txv_vp_poderuno.text.toString().trim()
        val poderdos = txv_vp_poderdos.text.toString().trim()
        val fecha = txv_vp_fechaCaptura.text.toString().trim()
        val nivel = txv_vp_nivel.text.toString().trim()
        val latitud = txv_vp_latitud.text.toString().trim()
        val longitud = txv_vp_longitud.text.toString().trim()

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
                Integer.valueOf(id), nombre, poderuno, poderdos, fecha,
                Integer.valueOf(nivel), latitud, longitud, idEntrenadorGlobal
            )
            conectarBackendPut(nuevoPokemon)
        }

    }

    private fun conectarBackendPut(nuevoPokemon: ClasePokemonParcelable) {

        val url = ClaseServidorBackend.getURL("pokemon/${nuevoPokemon.idPokemon}")
        val pokemonACrear = listOf(
            "nombrePokemon" to nuevoPokemon.nombrePokemon,
            "poderUno" to nuevoPokemon.poderUno,
            "poderDos" to nuevoPokemon.poderDos,
            "fechaCaptura" to nuevoPokemon.fechaCaptura,
            "nivel" to nuevoPokemon.nivel,
            "latitud" to nuevoPokemon.latitud,
            "longitud" to nuevoPokemon.longitud
        )

        Log.i("http", "Mi URL: $url")
        url.httpPut(pokemonACrear)
            .responseString { _, _, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http", "Error Put Pokemon: ${ex.message}")
                    }
                    is Result.Success -> {
                        Log.i("http", "Pokemon actualizado")
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
                    "pokemonActualizado",
                    "${DatosUsuario.obtenerUsuarioActual().nombreusuario} ha actualizado un pokemon")
                .putExtra("idEntrenadorActual", txv_idPokemon.text.toString())
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
    }
}
