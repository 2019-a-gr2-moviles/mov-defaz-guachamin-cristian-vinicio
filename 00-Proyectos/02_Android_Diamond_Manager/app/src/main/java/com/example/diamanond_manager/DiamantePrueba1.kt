package com.example.diamanond_manager

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_diamante_prueba1.*

class DiamantePrueba1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diamante_prueba1)

        Toast.makeText(applicationContext,
            "Bienvenid@ de nuevo ${DatosUsuario.obtenerUsuarioActual().nombreusuario}",
            Toast.LENGTH_SHORT).show()

        btn_gallery.setOnClickListener {
            startActivity(Intent(this, RecyclerViewDiamantes::class.java))
        }

        btn_manager.setOnClickListener {
            startActivity(Intent(this, FormularioInsercion::class.java))
        }
    }
}
