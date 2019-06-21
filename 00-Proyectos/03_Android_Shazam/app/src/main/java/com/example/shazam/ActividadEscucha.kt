package com.example.shazam

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_actividad_escucha.*

class ActividadEscucha : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad_escucha)

        btn_escuchar.setOnClickListener {
            irACancionEncontrada()
        }
    }

    private fun irACancionEncontrada(){
        val cancionEncontrada = ParcelableMusica(
            "Moving Mountains",
            "Disclosure",
            "Caracal",
            1
        )
        val intentExplicito = Intent(
            this,
            VerCancion::class.java
        )
        intentExplicito.putExtra("cancion", cancionEncontrada)
        intentExplicito.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intentExplicito)
    }
}
