package com.example.a05_layouts_recyclerview


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.Klaxon
import java.lang.Exception

class ConexionHTTP : AppCompatActivity() {

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
                          "nombre": "Adrian",
                          "fkEmpresa": 1,
                      }
                  ],
                  "createdAt": 1561663617636,
                  "updatedAt": 1561663617636,
                  "id": 1,
                  "nombre": "Manticore Labs"
                }
            ]
        """.trimIndent()

        try {
            val empresaInstancia = Klaxon()
                .parseArray<Empresa>(json)
            empresaInstancia?.forEach {
                Log.i("http",
                    "Nombre ${it.nombre}")

                Log.i("http",
                    "Id ${it.id}")

                Log.i("http",
                    "Fecha ${it.fechaCreacion}")

                it.usuarioDeEmpresa.forEach {
                    Log.i("http",
                        "Nombre ${it.nombre}")
                    Log.i("http",
                        "FK ${it.fkEmpresa}")
                }

            }
        } catch (e:Exception){
            Log.i("http","${e.message}")
            Log.i("http",
                "Error instanciando la empresa")
        }
    }
}
