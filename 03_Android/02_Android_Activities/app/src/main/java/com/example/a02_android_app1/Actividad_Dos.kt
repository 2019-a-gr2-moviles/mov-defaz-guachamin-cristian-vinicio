package com.example.a02_android_app1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_actividad__dos.*

class Actividad_Dos : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad__dos)
        // Llamando a MainActivity desde boton
        val nombre: String? = intent.getStringExtra("nombre") // pueden ser nulos 'nullable'
        val edad: Int? = intent.getIntExtra("edad",0)
        println(nombre)
        println(edad)
        Log.i("intents","Nombre $nombre")
        Log.i("intents","Edad $edad")

        btn_actividad_uno.setOnClickListener{
            irAActividadUno()
        }

    }

    private fun irAActividadUno(){
        val intent = Intent(
            this,
            MainActivity::class.java
        )
        // Si colocamos los flags aqui, finaliza el programa.
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}
