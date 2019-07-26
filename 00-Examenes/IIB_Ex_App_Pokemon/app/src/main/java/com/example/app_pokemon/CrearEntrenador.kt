package com.example.app_pokemon

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_crear_entrenador.*

class CrearEntrenador : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_entrenador)

        btn_crear_entrenador.setOnClickListener {
            crearEntrenador()
        }
    }

    private fun crearEntrenador() {
        val nombre = txv_ce_nombre.text.toString()
        val apellido = txv_ce_apellido.text.toString()
        val fechaNac = txv_ce_fecha.text.toString()
        val medallas = Integer.valueOf(txv_ce_medallas.text.toString())

        if (nombre == "" || apellido == "" || fechaNac == "" || medallas.toString() == "") {
            Toast.makeText(
                applicationContext,
                "Complete el formulario",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            val nuevoEntrenador = ClaseEntrenador(
                0, nombre, apellido, fechaNac, medallas, false
            )
            conectarBackendPost(nuevoEntrenador)
        }
    }

    private fun conectarBackendPost(nuevoEntrenador: ClaseEntrenador) {

        val url = ClaseServidorBackend.getURL("entrenador")
        val entrenadorACrear = """
            {
            nombreEntrenador: "${nuevoEntrenador.nombreEntrenador}",
            apellidoEntrenador: "${nuevoEntrenador.apellidoEntrenador}",
            fechaNac: "${nuevoEntrenador.fechaNac}",
            medallas: ${nuevoEntrenador.medallas},
            campeonActual: ${nuevoEntrenador.campeonActual}
            }
        """.trimIndent()
        url.httpPost().body(entrenadorACrear)
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http", "Error Post Entrenador: ${ex.message}")
                    }
                    is Result.Success -> {
                        Log.i("http", "Entrenador creado $request /n Response $response")
                        // Cerrar la actividad y volver al menu
                    }
                }
            }
    }

}
