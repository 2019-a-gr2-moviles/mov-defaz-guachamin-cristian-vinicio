package com.example.app_pokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
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
        val nombre = txv_ce_nombre.text.toString().trim()
        val apellido = txv_ce_apellido.text.toString().trim()
        val fechaNac = txv_ce_fecha.text.toString().trim()
        val medallas = txv_ce_medallas.text.toString().trim()

        if (nombre == "" || apellido == "" || fechaNac == "" || medallas == "") {
            Toast.makeText(
                applicationContext,
                "Complete el formulario",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            val nuevoEntrenador = ClaseEntrenadorParcelable(
                0, nombre, apellido, fechaNac, Integer.valueOf(medallas), false
            )
            conectarBackendPost(nuevoEntrenador)
        }
    }

    private fun conectarBackendPost(nuevoEntrenador: ClaseEntrenadorParcelable) {

        val url = ClaseServidorBackend.getURL("entrenador")
        Log.i("http", "Mi URL: $url")

        val entrenadorACrear2 = listOf(
            "nombreEntrenador" to nuevoEntrenador.nombreEntrenador,
            "apellidoEntrenador" to nuevoEntrenador.apellidoEntrenador,
            "fechaNac" to nuevoEntrenador.fechaNac,
            "medallas" to nuevoEntrenador.medallas,
            "campeonActual" to nuevoEntrenador.campeonActual
        )

        url.httpPost(entrenadorACrear2)
            .responseString { _, _, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http", "Error Post Entrenador: ${ex.message}")
                    }
                    is Result.Success -> {
                        Log.i("http", "Entrenador creado")
                        notificarInsercion()
                    }
                }
            }
    }

    private fun notificarInsercion(){
        startActivity(
            Intent(this,
                ListViewEntrenador::class.java )
                .putExtra(
                    "entrenadorCreado",
                    "${DatosUsuario.obtenerUsuarioActual().nombreusuario} ha creado un nuevo entrenador")
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
    }

}
