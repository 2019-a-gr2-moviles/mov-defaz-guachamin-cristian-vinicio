package com.example.json_http

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.result.Result.Failure
import com.github.kittinunf.result.Result.Success
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost

class ConexionHttpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conexion_http)

        val json = """
            [
                {
                  "usuariosDeEmpresa": [
                      {
                          "createdAt": 1561663617636,
                          "updatedAt": 1561663617636,
                          "id": 1,
                          "nombre": "Criss",
                          "fkEmpresa": 1,
                      }
                  ],
                  "createdAt": 1561663617636,
                  "updatedAt": 1561663617636,
                  "id": 1,
                  "nombreEmpresa": "Manticore Labs"
                }
            ]
        """.trimIndent()

        try {
            val empresaInstancia = Klaxon()
                .parseArray<Empresa>(json)

            empresaInstancia?.forEach {

                Log.i(
                    "http",
                    "Nombre ${it.nombre}"
                )

                Log.i(
                    "http",
                    "Id ${it.id}"
                )

                Log.i(
                    "http",
                    "Fecha ${it.fechaCreacion}"
                )

                it.usuariosDeEmpresa.forEach {
                    Log.i(
                        "http",
                        "Nombre ${it.nombre}"
                    )
                    Log.i(
                        "http",
                        "FK ${it.fkEmpresa}"
                    )
                }

            }
        } catch (e: Exception) {
            Log.i("http", "${e.message}")
            Log.i(
                "http",
                "Error instanciando la empresa"
            )
        }


        val url = "http://172.31.104.109:1337/empresa/1"


        url
            .httpGet()
            .responseString { request, response, result ->
                when (result) {
                    is Failure -> {
                        val ex = result.getException()
                        Log.i("httpError", "Error: ${ex.message}")
                    }
                    is Success -> {
                        val data = result.get()
                        Log.i("httpSuccess", "Data: $data")

                        val empresaParseada = Klaxon()
                            .parse<Empresa>(data)
                        if (empresaParseada != null) {
                            Log.i("http"," iiiiiiiiiiiiiiiiiiii ")
                            Log.i("http","${empresaParseada.nombre} ")
                            Log.i("http","${empresaParseada.id} ")
                        }
                    }
                }
            }

        // Enviar datos con post
        val urlCrearEmpresa = "http://172.31.104.109:1337/empresa"
        val parametroCrearEmpresa = listOf(
            "nombre" to "Manticore Labs 2", // Este sirve
            "apellido" to "Defz", // colados
            "sueldo" to 12.20, // colados
            "casado" to false, // colados
            "hijos" to null // colados
        )

        urlCrearEmpresa // Get -> parametros de consulta, no de cuerpo.
            .httpPost(parametroCrearEmpresa)
            .responseString { request, response, result ->
                when(result){
                    is Failure -> {
                        val error = result.getException()
                        Log.i("http","Error: $error")
                    }
                    is Success -> {
                        val empresaString = result.get();
                        Log.i("http", "Mensaje: $empresaString")
                    }
                }
            }
    }
}
